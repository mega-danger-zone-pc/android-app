package com.example.mega_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.animation.ObjectAnimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    final int interval = 30;
    final int total = 3000;
    final int steps = total / interval;

    SharedPreferences sPref;
    final String SAVED_ACTIVITY_SHOW = "first_activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar = findViewById(R.id.linearProgressIndicator);
        TextView text = findViewById(R.id.textProc);
        sPref = getPreferences(MODE_PRIVATE);

        final Handler progressHandler = new Handler();
        progressHandler.post(new Runnable() {
            int progress = 0;
            @Override
            public void run() {
                progress += 100 / steps;
                progressBar.setProgress(progress);
                text.setText(String.format("%s: %d%%", getString(R.string.loading), progress));

                if (progress < 100) {
                    progressHandler.postDelayed(this, 30);
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
        setContentView(R.layout.activity_first);

        final View rootView = findViewById(android.R.id.content);

        rootView.setAlpha(0f);

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(rootView, "alpha", 0f, 1f);
        fadeIn.setDuration(1000);

        fadeIn.start();
    }
    
    public void onNextMain(View view) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putBoolean(SAVED_ACTIVITY_SHOW, true);
        ed.apply();
        toMain();
    }

    private void toMain(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}