package com.example.mega_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {
    private EditText editEmail, editPassword, editPassword2, editFirstName, editAdress, editPostalIndex;
    private Button buttonEnter;
    private ImageButton imageShowPass, imageShowPass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPassword2 = findViewById(R.id.editPassword2);
        editFirstName = findViewById(R.id.editFirstName);
        editAdress = findViewById(R.id.editAdress);
        editPostalIndex = findViewById(R.id.editPostalIndex);
        imageShowPass = findViewById(R.id.imageShowPass);
        imageShowPass2 = findViewById(R.id.imageShowPass2);
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

        imageShowPass2.setOnClickListener(view -> {
            if (editPassword2.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                editPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageShowPass2.setImageResource(R.drawable.ic_eye_closed);
            } else {
                editPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageShowPass2.setImageResource(R.drawable.ic_eye);
            }
        });
//http://10.24.5.79:44373/api/Computers

        buttonEnter.setOnClickListener(view -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            String confPassword = editPassword2.getText().toString().trim();

            if (!Checker.isValidEmail(email)){
                Toast.makeText(this, getResources().getText(R.string.incorrect_email), Toast.LENGTH_LONG).show();
                return;
            }
            if (!Checker.isValidPassword(password)){
                Toast.makeText(this, getResources().getText(R.string.invalid_password), Toast.LENGTH_LONG).show();
                return;
            }
            if (!Checker.isValidPassword(confPassword)){
                Toast.makeText(this, getResources().getText(R.string.invalid_password), Toast.LENGTH_LONG).show();
                return;
            }

            if (!Checker.doPasswordsMatch(password, confPassword)) {
                Toast.makeText(this, R.string.password_incorret, Toast.LENGTH_SHORT).show();
                return;
            }

            toLoginActivity();
        });
    }

    public void onClickToAuth(View view) {
        toLoginActivity();
    }
    private void toLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}