package com.madcodes.CSafe.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.madcodes.CSafe.R;

public class CustomProgressbar extends Dialog {
    private static CustomProgressbar mCustomProgressbar;
    private CustomProgressbar mProgressbar;
    private OnDismissListener mOnDissmissListener;
    LottieAnimationView lav_actionBar;

    private CustomProgressbar(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progressbar);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        lav_actionBar = findViewById(R.id.lav_actionBar);
        startAnimation();
    }

    public CustomProgressbar(Context context, Boolean instance) {
        super(context);
        mProgressbar = new CustomProgressbar(context);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mOnDissmissListener != null) {
            mOnDissmissListener.onDismiss(this);
        }
    }

    public static void showProgressBar(Context context, boolean cancelable) {
        showProgressBar(context, cancelable, null);
    }

    public static void showProgressBar(Context context, boolean cancelable, String message) {
        if (mCustomProgressbar != null && mCustomProgressbar.isShowing()) {
            mCustomProgressbar.cancel();
        }
        mCustomProgressbar = new CustomProgressbar(context);
        mCustomProgressbar.setCancelable(cancelable);
        mCustomProgressbar.show();

    }

    public static void showProgressBar(Context context, OnDismissListener listener) {

        if (mCustomProgressbar != null && mCustomProgressbar.isShowing()) {
            mCustomProgressbar.cancel();
        }
        mCustomProgressbar = new CustomProgressbar(context);
        mCustomProgressbar.setListener(listener);
        mCustomProgressbar.setCancelable(Boolean.TRUE);
        mCustomProgressbar.show();
    }

    public static void hideProgressBar() {
        if (mCustomProgressbar != null) {
            mCustomProgressbar.dismiss();
        }
    }

    private void setListener(OnDismissListener listener) {
        mOnDissmissListener = listener;

    }

    public static void showListViewBottomProgressBar(View view) {
        if (mCustomProgressbar != null) {
            mCustomProgressbar.dismiss();
        }

        view.setVisibility(View.VISIBLE);
    }

    public static void hideListViewBottomProgressBar(View view) {
        if (mCustomProgressbar != null) {
            mCustomProgressbar.dismiss();
        }

        view.setVisibility(View.GONE);
    }

    public void showProgress(Context context, boolean cancelable, String message) {

        if (mProgressbar != null && mProgressbar.isShowing()) {
            mProgressbar.cancel();
        }
        mProgressbar.setCancelable(cancelable);
        mProgressbar.show();
    }

    private void startAnimation() {
        //lav_actionBar.setProgress(0);
        //  lav_actionBar.pauseAnimation();
        lav_actionBar.playAnimation();
        lav_actionBar.setRepeatMode(LottieDrawable.RESTART);

    }
}