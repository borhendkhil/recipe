package com.example.recipe.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.recipe.R;
import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.Planing;
import com.google.android.material.textfield.TextInputEditText;


public class Profil extends Fragment {
    TextInputEditText etName, etDate, etTime;
    Button btn;
    DatabaseHandler db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profil, container, false);

        etName = view.findViewById(R.id.name);
        etDate = view.findViewById(R.id.date);
        etTime = view.findViewById(R.id.time);
        btn = view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                String time = etTime.getText().toString().trim();
                Planing planing = new Planing(name, date, time);
                 db = new DatabaseHandler(getActivity());
                db.addPlaning(planing);
                Toast.makeText(view.getContext(), "ajouter avec succès", Toast.LENGTH_SHORT).show();
                etDate.setText("");
                etName.setText("");
                etTime.setText("");
            }
        });





        return view;
    }
}