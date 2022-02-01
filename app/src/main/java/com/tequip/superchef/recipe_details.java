package com.tequip.superchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class recipe_details extends AppCompatActivity {
    private int position;
    TextView RecipeName, UsedIngredients, UnusedIngredients, MissingIngredients, RecipeInfo, Process, Available, Unavailabe, ingredientsRequired;
    Button WatchOnYouTube;
    ImageView RecipeImage;
    ScrollView MainLayout;
    ProgressBar Loader;
    String RecipeImageUrl;
    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        RecipeName = findViewById(R.id.recipe_name);
        UsedIngredients = findViewById(R.id.used_ingredients);
        UnusedIngredients = findViewById(R.id.unused_ingredients);
        MissingIngredients = findViewById(R.id.missing_ingredients);
        Process = findViewById(R.id.Recipe_title);
        Available = findViewById(R.id.Available);
        Unavailabe = findViewById(R.id.Unavailable);
        ingredientsRequired = findViewById(R.id.ingredientsRequired);

        RecipeImage = findViewById(R.id.recipe_image);
        RecipeInfo = findViewById(R.id.recipe_info);
        MainLayout = findViewById(R.id.main_layout);
        Loader = findViewById(R.id.main_loader_recipe_details);
        WatchOnYouTube = findViewById(R.id.watchOnYoutube);

        RecipeName.setText(Recipe_page.recipe_model_list.get(position).getRecipeName());
        UsedIngredients.setText(Recipe_page.recipe_model_list.get(position).getUsedIngredients());
        if (UsedIngredients.getText().toString().isEmpty()) {
            ingredientsRequired.setVisibility(View.GONE);
        }
        UnusedIngredients.setText(Recipe_page.recipe_model_list.get(position).getUnusedIngredients());
        if (UnusedIngredients.getText().toString().isEmpty()) {
            Available.setVisibility(View.GONE);
        }
        MissingIngredients.setText(Recipe_page.recipe_model_list.get(position).getMissingIngredients());
        if (MissingIngredients.getText().toString().isEmpty()) {
            Unavailabe.setVisibility(View.GONE);
        }
        RecipeImageUrl = Recipe_page.recipe_model_list.get(position).getRecipeImage();
        Glide.with(this).load(RecipeImageUrl).into(RecipeImage);
        Id = Integer.parseInt(Recipe_page.recipe_model_list.get(position).getId());

        findRecipe(Id);

        // Youtube Click search function
        WatchOnYouTube.setOnClickListener(view -> {
            String recipeName = RecipeName.getText().toString();
            gotoUrl("https://www.youtube.com/results?search_query=" + recipeName);
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
// Function to find the recipe using the ID and Displaying the relevant information of the Recipe According to the ingredients
    private void findRecipe(int id) {
        Loader.animate();
        String url = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=3c7007cd099044afb27dc480f930433d";
        StringRequest requestData = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String instructions = jsonObject.getString("instructions");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    RecipeInfo.setText(Html.fromHtml(instructions, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    RecipeInfo.setText(Html.fromHtml(instructions));
                }
                if(RecipeInfo.getText().toString().isEmpty()){
                    Process.setVisibility(View.GONE);
                }
                Loader.clearAnimation();
                Loader.setVisibility(View.GONE);
                MainLayout.setVisibility(View.VISIBLE);

            } catch (Exception e) {
                e.printStackTrace();
                Loader.clearAnimation();
                Loader.setVisibility(View.GONE);
            }
        }, error -> {
            Toast.makeText(recipe_details.this, "Please switch on your Wifi or Mobile Data and try again later", Toast.LENGTH_LONG).show();
            Loader.clearAnimation();
            Loader.setVisibility(View.GONE);
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestData);
    }
}