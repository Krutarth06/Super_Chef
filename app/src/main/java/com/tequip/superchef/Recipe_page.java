package com.tequip.superchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recipe_page extends AppCompatActivity {
    ListView RecipeListView;
    ProgressBar loader;
    TextView showInfoAboutRecipe;
    public static List<recipe_model_class> recipe_model_list = new ArrayList<>();
    recipe_model_class ModelRecipe;
    recipe_custom_adapter myCustomAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);

        RecipeListView = findViewById(R.id.recipe_listView);
        loader = findViewById(R.id.main_loader_activity_main);
        showInfoAboutRecipe = findViewById(R.id.displayingResult);
        loader.setVisibility(View.VISIBLE);
        recipe_model_list.clear();
        RecipeListView.setAdapter(null);

        String S = MainActivity.ingredientString;
        S = S.substring(0, S.length() - 2);
        String St = "Displaying food items for: " + S + ".";
        showInfoAboutRecipe.setText(St);
        showInfoAboutRecipe.setBackgroundColor(Color.parseColor("#ffffff"));
        showInfoAboutRecipe.setTextColor(Color.parseColor("#000000"));
        showInfoAboutRecipe.setTextSize(17);
        showInfoAboutRecipe.setTypeface(showInfoAboutRecipe.getTypeface(), Typeface.BOLD);
        fetchData(MainActivity.ingredientString);


        RecipeListView.setOnItemClickListener((adapterView, view, position, id) -> startActivity(new Intent(getApplicationContext(), recipe_details.class).putExtra("position", position)));
    }

    // function to fetch the data from the API and convert it from JSON to string response and then display it using a ListView using adapter
    private void fetchData(String ingredientText) {
        loader.animate();
        String url = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=3c7007cd099044afb27dc480f930433d&ingredients=" + ingredientText;
        StringRequest requestData = new StringRequest(Request.Method.GET, url, response -> {
            try {

                if (response.equals("[]")) {
                    Toast.makeText(Recipe_page.this, "No results found. Please enter the ingredients again.", Toast.LENGTH_LONG).show();
                    loader.clearAnimation();
                    loader.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {

                    JSONArray recipeArray = new JSONArray(response);
                    ArrayList<String> MissedIngredientArrayList = new ArrayList<>();
                    ArrayList<String> UsedIngredientArrayList = new ArrayList<>();
                    ArrayList<String> UnUsedIngredientArrayList = new ArrayList<>();
                    for (int i = 0; i < recipeArray.length(); i++) {
                        JSONObject recipeObject = recipeArray.getJSONObject(i);
                        String id = recipeObject.getString("id");
                        String title = recipeObject.getString("title");
                        String image = recipeObject.getString("image");


                        JSONArray missedIngredientArray = recipeObject.getJSONArray("missedIngredients");
                        for (int j = 0; j < missedIngredientArray.length(); j++) {
                            JSONObject MissedObject = missedIngredientArray.getJSONObject(j);
                            String missedIngredients1 = MissedObject.getString("original");
                            MissedIngredientArrayList.add(missedIngredients1);
                        }
                        JSONArray UsedIngredientArray = recipeObject.getJSONArray("usedIngredients");
                        for (int j = 0; j < UsedIngredientArray.length(); j++) {
                            JSONObject UsedObject = UsedIngredientArray.getJSONObject(j);
                            String usedIngredients1 = UsedObject.getString("original");
                            UsedIngredientArrayList.add(usedIngredients1);
                        }
                        JSONArray UnusedIngredientArray = recipeObject.getJSONArray("unusedIngredients");
                        for (int j = 0; j < UnusedIngredientArray.length(); j++) {
                            JSONObject UnusedObject = UnusedIngredientArray.getJSONObject(j);
                            String UnusedIngredients1 = UnusedObject.getString("original");
                            UnUsedIngredientArrayList.add(UnusedIngredients1);
                        }

                        String missedIngredients = TextUtils.join("\n", MissedIngredientArrayList);
                        String usedIngredients = TextUtils.join("\n", UsedIngredientArrayList);
                        String unusedIngredients = TextUtils.join("\n", UnUsedIngredientArrayList);

                        ModelRecipe = new recipe_model_class(id, title, image, usedIngredients, unusedIngredients, missedIngredients);
                        recipe_model_list.add(ModelRecipe);

                    }
                    myCustomAdapter1 = new recipe_custom_adapter(Recipe_page.this, recipe_model_list);
                    RecipeListView.setAdapter(myCustomAdapter1);

                    loader.clearAnimation();
                    loader.setVisibility(View.GONE);
                    RecipeListView.setVisibility(View.VISIBLE);

                }
            } catch (Exception e) {
                e.printStackTrace();
                loader.clearAnimation();
                loader.setVisibility(View.GONE);
            }
        }, error -> {
            Toast.makeText(Recipe_page.this, "Please switch on your wifi or mobile Data and try again later", Toast.LENGTH_LONG).show();
            loader.clearAnimation();
            loader.setVisibility(View.GONE);
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestData);
    }
}