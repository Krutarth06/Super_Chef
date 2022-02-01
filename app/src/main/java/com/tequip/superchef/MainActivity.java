package com.tequip.superchef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        MultipleSelectIngredientIndian.onMultiChoiceListenerIndian,
        MultipleSelectIngredientVegetable.onMultiChoiceListenerVegetable,
        MultipleSelectIngredientHerbsAndSpices.onMultiChoiceListenerHerbsAndSpices,
        MultipleSelectIngredientBeverages.onMultiChoiceListenerBeverages,
        MultipleSelectIngredientDesserts.onMultiChoiceListenerDesserts,
        MultipleSelectIngredientMeat.onMultiChoiceListenerMeat,
        MultipleSelectIngredientCheese.onMultiChoiceListenerCheese {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    EditText ingredients;
    ImageView AddRecipe;
    LinearLayout RecipeList;
    Button find_recipe;
    TextView Vegetable_title, HerbsSpices_title, Beverages_title, Desserts_title, Meat_title, Cheese_title, Indian_title;
    public static String IngredientsName, ingredientString;

    ArrayList<String> IngredientList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar_id);

        RecipeList = findViewById(R.id.RecipeList);
        AddRecipe = findViewById(R.id.addRecipe);

        ingredients = findViewById(R.id.ingredients);
        find_recipe = findViewById(R.id.find_recipe_btn);

        Indian_title = findViewById(R.id.indian_title);
        Vegetable_title = findViewById(R.id.vegetable_title);
        HerbsSpices_title = findViewById(R.id.herbsSpices_title);
        Beverages_title = findViewById(R.id.beverages_title);
        Desserts_title = findViewById(R.id.desserts_title);
        Meat_title = findViewById(R.id.meat_title);
        Cheese_title = findViewById(R.id.cheese_title);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // ADD button
        AddRecipe.setOnClickListener(view -> {
            IngredientsName = ingredients.getText().toString();
            if (IngredientsName.equals("")) {
                Toast.makeText(getApplicationContext(), "Please enter the ingredient", Toast.LENGTH_SHORT).show();
            } else {
                IngredientList.add(IngredientsName);
                ingredients.setText("");
                updateView();
            }
        });
        // Indian items button
        Indian_title.setOnClickListener(view -> {
            DialogFragment multiChoiceDialog = new MultipleSelectIngredientIndian();
            multiChoiceDialog.setCancelable(false);
            multiChoiceDialog.show(getSupportFragmentManager(), "multiChoice dialog");
        });

        // Vegetable items button
        Vegetable_title.setOnClickListener(view -> {
            DialogFragment multiChoiceDialog = new MultipleSelectIngredientVegetable();
            multiChoiceDialog.setCancelable(false);
            multiChoiceDialog.show(getSupportFragmentManager(), "multiChoice dialog");
        });

        // Herbs and Spices items button
        HerbsSpices_title.setOnClickListener(view -> {
            DialogFragment multiChoiceDialog = new MultipleSelectIngredientHerbsAndSpices();
            multiChoiceDialog.setCancelable(false);
            multiChoiceDialog.show(getSupportFragmentManager(), "multiChoice dialog");
        });

        Beverages_title.setOnClickListener(view -> {
            DialogFragment multiChoiceDialog = new MultipleSelectIngredientBeverages();
            multiChoiceDialog.setCancelable(false);
            multiChoiceDialog.show(getSupportFragmentManager(), "multiChoice dialog");
        });

        Desserts_title.setOnClickListener(view -> {
            DialogFragment multiChoiceDialog = new MultipleSelectIngredientDesserts();
            multiChoiceDialog.setCancelable(false);
            multiChoiceDialog.show(getSupportFragmentManager(), "multiChoice dialog");
        });

        Meat_title.setOnClickListener(view -> {
            DialogFragment multiChoiceDialog = new MultipleSelectIngredientMeat();
            multiChoiceDialog.setCancelable(false);
            multiChoiceDialog.show(getSupportFragmentManager(), "multiChoice dialog");
        });

        Cheese_title.setOnClickListener(view -> {
            DialogFragment multiChoiceDialog = new MultipleSelectIngredientCheese();
            multiChoiceDialog.setCancelable(false);
            multiChoiceDialog.show(getSupportFragmentManager(), "multiChoice dialog");
        });

        // search recipe button
        find_recipe.setOnClickListener(v -> {
            ingredientString = "";
            for (int i = 0; i < IngredientList.size(); i++) {
                ingredientString += IngredientList.get(i) + ", ";
            }
            if (ingredientString.equals("")) {
                Toast.makeText(getApplicationContext(), "Please Add the ingredients", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(getApplicationContext(), Recipe_page.class));
                IngredientList.clear();
                RecipeList.removeAllViews();
                find_recipe.setVisibility(View.GONE);

            }
        });
    }

    //Hamburger Menu Items and Functionality
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_about_us:
                startActivity(new Intent(getApplicationContext(), about_us.class));
                break;
            case R.id.nav_privacy_policy:
                startActivity(new Intent(getApplicationContext(), privacy_policy.class));
                break;
            case R.id.nav_share_app:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plane");
                share.putExtra(Intent.EXTRA_TEXT, "Hey, Super Chef is an android app made by TeQuip Software Solutions that I use to check recipes. Get it for free at: https://play.google.com/store/apps/details?id=com.tequip.superchef");
                startActivity(share);
                break;
            case R.id.nav_rate_app:
                gotoUrl("https://play.google.com/store/apps/details?id=com.tequip.superchef");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.side_menu, menu);
        return true;
    }
    //Thee Dots Menu Items and Functionality
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_wp:

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?text= Hey, Super Chef is an android app made by TeQuip Software Solutions that I use to check recipes. Get it for free at : https://play.google.com/store/apps/details?id=com.tequip.superchef"));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(this, "Whatsapp is not installed on your device.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.rate_us:
                gotoUrl("https://play.google.com/store/apps/details?id=com.tequip.superchef");
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
// function to update the ingredient item list on addition or subtraction of an ingredient
    private void updateView() {
        RecipeList.removeAllViewsInLayout();
        for (int i = 0; i < IngredientList.size(); i++) {
            String st = IngredientList.get(i);
            TextView textView = createTextView(st);
            RecipeList.addView(textView);

        }
        if (IngredientList.isEmpty()) {
            find_recipe.setVisibility(View.GONE);
        } else {
            find_recipe.setVisibility(View.VISIBLE);
        }
    }
    // function to create the ingredient item list views on addition or subtraction of an ingredient
    private TextView createTextView(String s) {
        TextView textView = new TextView(getApplicationContext());
        textView.setText(s);
        textView.setBackgroundColor(Color.parseColor("#ffffff"));
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setPadding(30, 20, 30, 20);
        textView.setTextSize(16);
        textView.setBackgroundResource(R.drawable.recipe_item_design);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 20, 0);
        textView.setLayoutParams(params);
        return textView;
    }
  // function to parse string to URL
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
// popups functionalities for ADD and CANCEL
    @Override
    public void onPositiveButtonClickedIndian(String[] list, ArrayList<String> SelectedItemList) {
        IngredientList.addAll(SelectedItemList);
        updateView();
    }

    @Override
    public void onNegativeButtonClickedIndian() {
    }

    @Override
    public void onPositiveButtonClickedVegetable(String[] list, ArrayList<String> SelectedItemListVegetable) {
        IngredientList.addAll(SelectedItemListVegetable);
        updateView();
    }

    @Override
    public void onNegativeButtonClickedVegetable() {
    }

    @Override
    public void onPositiveButtonClickedHerbsAndSpices(String[] list, ArrayList<String> SelectedItemListHerbsAndSpices) {
        IngredientList.addAll(SelectedItemListHerbsAndSpices);
        updateView();
    }

    @Override
    public void onNegativeButtonClickedHerbsAndSpices() {
    }

    @Override
    public void onPositiveButtonClickedBeverages(String[] list, ArrayList<String> SelectedItemListBeverages) {
        IngredientList.addAll(SelectedItemListBeverages);
        updateView();
    }

    @Override
    public void onNegativeButtonClickedBeverages() {
    }

    @Override
    public void onPositiveButtonClickedDesserts(String[] list, ArrayList<String> SelectedItemListDesserts) {
        IngredientList.addAll(SelectedItemListDesserts);
        updateView();
    }

    @Override
    public void onNegativeButtonClickedDesserts() {
    }

    @Override
    public void onPositiveButtonClickedMeat(String[] list, ArrayList<String> SelectedItemListMeat) {
        IngredientList.addAll(SelectedItemListMeat);
        updateView();
    }

    @Override
    public void onNegativeButtonClickedMeat() {
    }

    @Override
    public void onPositiveButtonClickedCheese(String[] list, ArrayList<String> SelectedItemListCheese) {
        IngredientList.addAll(SelectedItemListCheese);
        updateView();
    }

    @Override
    public void onNegativeButtonClickedCheese() {
    }
}