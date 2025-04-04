package com.example.mega_app;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.ObjectAnimator;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Console;

public class SplashActivity extends AppCompatActivity {
    final int interval = 30;
    final int total = 3000;
    final int steps = total / interval;

    SharedPreferences sPref;
    public static final String SAVED_ACTIVITY_SHOW = "first_activity";
    public static final String SAVE_USER = "user";
    public static final String SAVED_EMAIL = "email";
    public static final String SAVED_PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar = findViewById(R.id.linearProgressIndicator);
        TextView text = findViewById(R.id.textProc);
        sPref = getPreferences(MODE_PRIVATE);

        ImageView imgExplosion = findViewById(R.id.imgExplosion);
        ImageView imgLogo = findViewById(R.id.imageView);

        Glide.with(this)
                .load(R.drawable.explosion)
                .into(imgExplosion);

        imgLogo.setVisibility(View.GONE);

        Handler handler = new Handler();

        handler.postDelayed(() -> {
            imgLogo.setVisibility(View.VISIBLE);

            handler.postDelayed(() -> {
                imgExplosion.setVisibility(View.GONE);
            }, 400);
        }, 1000);

        final Handler progressHandler = new Handler();
        progressHandler.post(new Runnable() {
            int progress = 0;
            @Override
            public void run() {
                progress += 100 / steps;
                progressBar.setProgress(progress);
                text.setText(String.format("%s: %d%%", getString(R.string.loading), progress));

                if (progress < 100) {
                    progressHandler.postDelayed(this, 20);
                } else {
                    if (!sPref.getBoolean(SAVED_ACTIVITY_SHOW, false)){
                        animateTransitionToFirstActivity();
                    }
                    else {
                        toMain();
                    }
                }
            }
        });
    }

    private void animateTransitionToFirstActivity() {
        final View rootView = findViewById(android.R.id.content);

        rootView.setAlpha(1f);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(rootView, "alpha", 1f, 0f);
        fadeOut.setDuration(1600);

        fadeOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setContentView(R.layout.activity_first);

                rootView.setAlpha(0f);

                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(rootView, "alpha", 0f, 1f);
                fadeIn.setDuration(1000);

                fadeIn.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        fadeOut.start();
    }
    
    public void onNextMain(View view) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putBoolean(SAVED_ACTIVITY_SHOW, true);
        ed.apply();
        toMain();
    }

    private void toMain(){
        SharedPreferences fsd = getSharedPreferences(SplashActivity.SAVE_USER, MODE_PRIVATE);
        String email = fsd.getString(SAVED_EMAIL, "");
        String password = fsd.getString(SAVED_PASSWORD, "");

        if (email.trim().isEmpty()|| password.trim().isEmpty()){
            startActivity(new Intent(this, LoginActivity.class));
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}