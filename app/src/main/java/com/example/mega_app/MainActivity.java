package com.example.mega_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = null;
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
    }

    public void onClickSvo(View view) {
        FrameLayout frameSvoy = findViewById(R.id.frameSvoy);

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(frameSvoy, "alpha", 0f, 1f);
        fadeIn.setDuration(350);
        fadeIn.start();

        frameSvoy.setVisibility(View.VISIBLE);

        frameSvoy.setOnClickListener(v -> {
            closeOverlay(frameSvoy);
        });
    }

    private void closeOverlay(FrameLayout fourthLayer) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(fourthLayer, "alpha", 1f, 0f);
        fadeOut.setDuration(350);
        fadeOut.start();

        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                fourthLayer.setVisibility(View.GONE);
            }
        });
    }
}