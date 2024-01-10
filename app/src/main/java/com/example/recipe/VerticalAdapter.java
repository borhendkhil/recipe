package com.example.recipe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipe.model.Recipe;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Recipe> recipes;

    public VerticalAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public VerticalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.horizontalrecipe,parent,false);
        return new VerticalAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalAdapter.ViewHolder holder, int position) {
        final Recipe recipe = recipes.get(position);
        holder.titel.setText(String.valueOf(recipe.getName()));
        holder.calory.setText(String.valueOf(recipe.getCalory()));
        holder.description.setText(String.valueOf(recipe.getDescription()));

        byte[] image = recipe.getImage();

        // Use Glide to load images asynchronously2
        if (image != null && image.length > 0) {
            Glide.with(context)
                    .asBitmap()
                    .load(image)
                    .into(holder.image);
        } else {

            Glide.with(context)
                    .load(R.drawable.logo)
                    .into(holder.image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Recipedetails.class);
                intent.putExtra("recipeName", recipe.getName());
                intent.putExtra("recipeCalory", recipe.getCalory());
                intent.putExtra("id", recipe.getId());
                intent.putExtra("category", recipe.getCategory());
                intent.putExtra("discription", recipe.getDescription());
                intent.putExtra("recipeImage", image);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titel ,calory ,description;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
                super(itemView);
                calory= itemView.findViewById(R.id.calory);
                titel = itemView.findViewById(R.id.titel);
                image = itemView.findViewById(R.id.image);
                description = itemView.findViewById(R.id.description);


        }
    }
}
