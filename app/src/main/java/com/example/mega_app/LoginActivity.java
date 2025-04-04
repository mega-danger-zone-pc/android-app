package com.example.mega_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button buttonEnter;
    private ImageButton imageShowPass;
    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sPref = getSharedPreferences(SplashActivity.SAVE_USER, MODE_PRIVATE);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        imageShowPass = findViewById(R.id.imageShowPass);
        buttonEnter = findViewById(R.id.buttonEnter);

        imageShowPass.setOnClickListener(view -> {
            if (editPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageShowPass.setImageResource(R.drawable.ic_eye_closed);
            } else {
                editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageShowPass.setImageResource(R.drawable.ic_eye);
            }
        });

        String email2 = sPref.getString(SplashActivity.SAVED_EMAIL, "");
        if (!email2.trim().isEmpty()){
            editEmail.setText(email2);
        }

        buttonEnter.setOnClickListener(view -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (!Checker.isValidEmail(email)){
                Toast.makeText(this, getResources().getText(R.string.incorrect_email), Toast.LENGTH_LONG).show();
                return;
            }
            if (!Checker.isValidPassword(password)){
                Toast.makeText(this, getResources().getText(R.string.invalid_password), Toast.LENGTH_LONG).show();
                return;
            }

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SplashActivity.SAVED_EMAIL, email);
            ed.putString(SplashActivity.SAVED_PASSWORD, password);
            ed.apply();

            startActivity(new Intent(this, MainActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    public void onClickToReg(View view) {
        startActivity(new Intent(this, RegActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}