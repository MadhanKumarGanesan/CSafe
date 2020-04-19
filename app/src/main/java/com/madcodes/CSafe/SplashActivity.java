package com.madcodes.CSafe;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.madcodes.CSafe.Utils.CSafePreferences;


public class SplashActivity extends AppCompatActivity {
    private LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();
        startCheckAnimation();
        moveTONextScreen();
    }

    private void initViews() {
        animationView = (LottieAnimationView)findViewById(R.id.lottieAnimationView);


    }

    private void startCheckAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(4500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animationView.setProgress((Float) valueAnimator.getAnimatedValue());
            }
        });

        if (animationView.getProgress() == 0f) {
            animator.start();
        } else {
            animationView.setProgress(0f);
        }
    }


    private void moveTONextScreen() {

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    if(CSafePreferences.getIsLogin()){
                        Intent mainIntent = new Intent(SplashActivity.this, NavigationActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                    else{
                        Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }

                }
            }, 4000);




    }

}
