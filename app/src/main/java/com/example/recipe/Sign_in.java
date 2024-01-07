package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe.controller.DatabaseHandler;
import com.google.android.material.textfield.TextInputEditText;

public class Sign_in extends AppCompatActivity {
    TextInputEditText email;
    TextInputEditText password;
    Button signIn;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if (emailText.isEmpty()) {
                    Toast.makeText(Sign_in.this, "Email est vide", Toast.LENGTH_SHORT).show();
                }else if (passwordText.isEmpty()) {
                    Toast.makeText(Sign_in.this, "Mot de passe est vide ", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseHandler db = new DatabaseHandler(Sign_in.this);
                    if (db.checkUser(emailText, passwordText)) {
                        Intent intent = new Intent(Sign_in.this, Menu.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(Sign_in.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            }});
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Sign_in.this, Sign_up.class);
                startActivity(intent);

            }
        });





    }
}