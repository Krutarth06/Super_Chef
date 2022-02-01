package com.tequip.superchef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class recipe_custom_adapter extends ArrayAdapter<recipe_model_class> {
    private Context context;
    private List<recipe_model_class> recipe_model_list;

    public recipe_custom_adapter(@NonNull Context context, List<recipe_model_class> recipe_model_list) {
        super(context, R.layout.list_item, recipe_model_list);
        this.context = context;
        this.recipe_model_list = recipe_model_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, true);
        TextView recipe_name = view.findViewById(R.id.recipe_title);
        ImageView recipe_image = view.findViewById(R.id.recipe_image);
        recipe_name.setText(recipe_model_list.get(position).getRecipeName());
        Glide.with(context).load(recipe_model_list.get(position).getRecipeImage()).into(recipe_image);
        return view;
    }
}
