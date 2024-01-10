package com.example.recipe;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.User;

import java.util.ArrayList;

public class UserRecycler extends RecyclerView.Adapter<UserRecycler.VHolder> {
    private Context context;
    private ArrayList<User> users;
    public UserRecycler(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new VHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecycler.VHolder holder, int position) {
        final User user = users.get(position);
        String username = user.getNom() + " " + user.getPrenom();
        holder.nom.setText(username);
        holder.email.setText(String.valueOf(user.getEmail()));


        //button delete
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(context);
                db.deleteUser(user.getId());

                users.remove(holder.getAdapterPosition());


                notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount() {

        return users.size();
    }


    public class VHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView email;
        ImageView delete;

        public VHolder(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nom);
            email = itemView.findViewById(R.id.email);
            delete = itemView.findViewById(R.id.delete);


        }
    }


}
