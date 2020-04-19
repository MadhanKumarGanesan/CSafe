package com.madcodes.CSafe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.madcodes.CSafe.Utils.CustomProgressbar;

public class WebViewActivity extends AppCompatActivity {

    private WebView activity_main_webview;
    private String url;
    private WebSettings spraySettings;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nearby);

        activity_main_webview = findViewById(R.id.activity_main_webview);
        activity_main_webview.setWebViewClient(new myWebClient());
        activity_main_webview.getSettings().setJavaScriptEnabled(true);


        activity_main_webview.getSettings().setSupportZoom(true);
        activity_main_webview.getSettings().setBuiltInZoomControls(true);
        activity_main_webview.setBackgroundColor(Color.parseColor("#DFDEDE"));
        activity_main_webview.getSettings().setUseWideViewPort(true);
        activity_main_webview.getSettings().setLoadWithOverviewMode(false);
        activity_main_webview.loadUrl(getIntent().getExtras().getString("URL"));
    }
    public class myWebClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            CustomProgressbar.showProgressBar(WebViewActivity.this, false);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            CustomProgressbar.hideProgressBar();


        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(WebViewActivity.this, NavigationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
