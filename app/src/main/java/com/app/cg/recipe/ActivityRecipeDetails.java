package com.app.cg.recipe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.cg.recipe.data.Tools;
import com.app.cg.recipe.model.Recipe;
import com.app.cg.recipe.data.Utils;

public class ActivityRecipeDetails extends AppCompatActivity {

    public static final String EXTRA_OBJCT = "com.app.cg.recipe.OBJ";

    private Recipe recipe;
    private FloatingActionButton fab;
    private View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        parent_view = findViewById(android.R.id.content);

        recipe = (Recipe) getIntent().getSerializableExtra(EXTRA_OBJCT);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabToggle();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(recipe.getName());

        ((ImageView) findViewById(R.id.image)).setImageResource(recipe.getPhoto());

        LinearLayout ingredients = (LinearLayout) findViewById(R.id.ingredients);
        TextView instructions = (TextView) findViewById(R.id.instructions);

        String[] title_ingredients = getResources().getStringArray(R.array.ingredients);
        addIngredientsList(ingredients, title_ingredients);

        instructions.setText(Html.fromHtml(getString(R.string.instruction)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isIdExist(getApplicationContext(), recipe.getId() + "")) {
                    Utils.delFavoriteId(getApplicationContext(), recipe.getId() + "");
                    Snackbar.make(parent_view, recipe.getName() + " remove from favorites", Snackbar.LENGTH_SHORT).show();
                } else {
                    Utils.addFavoriteId(getApplicationContext(), recipe.getId() + "");
                    Snackbar.make(parent_view, recipe.getName() + " added to favorites", Snackbar.LENGTH_SHORT).show();
                }
                fabToggle();
            }
        });

        // for system bar in lollipop
        Tools.systemBarLolipop(this);
    }

    private void fabToggle(){
        if(Utils.isIdExist(this, recipe.getId()+"")){
            fab.setImageResource(R.drawable.ic_nav_favorites);
        }else{
            fab.setImageResource(R.drawable.ic_nav_favorites_outline);
        }
    }

    private void addIngredientsList(LinearLayout linearLayout, String[] title){
        for (int i = 0; i < title.length; i++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            CheckBox checkBox = new CheckBox(this);
            checkBox.setTextColor(getResources().getColor(R.color.material_grey_600));
            checkBox.setText(title[i]);
            ll.addView(checkBox);
            linearLayout.addView(ll);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }else{
            Snackbar.make(parent_view, item.getTitle()+" clicked", Snackbar.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_recipe_details, menu);
        return true;
    }
}
