package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class Sign_up extends AppCompatActivity {
    Button newcomple ;
    TextInputEditText nom , prenom , email , password, cpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        nom=findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.Cpassword);
        newcomple=findViewById(R.id.signup);
        newcomple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomText = nom.getText().toString();
                String prenomText = prenom.getText().toString();
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String cpasswordText = cpassword.getText().toString();
                if (nomText.isEmpty()) {
                    Toast.makeText(Sign_up.this, "Nom est vide", Toast.LENGTH_SHORT).show();
                    nom.setError("Nom est vide");
                }else if (prenomText.isEmpty()) {
                    Toast.makeText(Sign_up.this, "Prenom est vide", Toast.LENGTH_SHORT).show();
                    prenom.setError("Prenom est vide");
                }else if (emailText.isEmpty()) {
                    Toast.makeText(Sign_up.this, "Email est vide", Toast.LENGTH_SHORT).show();
                    email.setError("Email est vide");
                }else if (passwordText.isEmpty()) {
                    Toast.makeText(Sign_up.this, "Mot de passe est vide", Toast.LENGTH_SHORT).show();
                    password.setError("Mot de passe est vide");
                }else if (cpasswordText.isEmpty()) {
                    Toast.makeText(Sign_up.this, "Confirmer mot de passe est vide", Toast.LENGTH_SHORT).show();
                    cpassword.setError("Confirmer mot de passe est vide");
                }else if (passwordText.length() < 6) {
                    Toast.makeText(Sign_up.this, "Mot de passe doit etre superieur a 6", Toast.LENGTH_SHORT).show();
                    password.setError("Mot de passe doit etre superieur a 6");

                }else if (!passwordText.equals(cpasswordText)) {
                    Toast.makeText(Sign_up.this, "Mot de passe ne correspond pas", Toast.LENGTH_SHORT).show();
                    cpassword.setError("Mot de passe ne correspond pas");
                }
                //matbadlou chy lahne, ane ma raka7tha kan bessif. el admin by default
                else {
                    DatabaseHandler db = new DatabaseHandler(Sign_up.this);
                    User user = new User(nomText,prenomText,emailText,passwordText,"Client");
                    db.addUser(user);
                    Toast.makeText(Sign_up.this, "compte créé avec succès", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sign_up.this, Sign_in.class);
                    startActivity(intent);

                    finish();

                }




            }
        });
        




    }
}