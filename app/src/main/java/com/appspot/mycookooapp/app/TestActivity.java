package com.appspot.mycookooapp.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.appspot.mycookooapp.api.eventEndpoint.model.Event;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    private TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        showText = (TextView)findViewById(R.id.textView2);


        List<Event> myEventList = ((CookooMainApplication) this.getApplication()).getGlobalEventList();
        BasicFunctions bf = new BasicFunctions();
        List<SuggestedRecipe> mySRecipeList = bf.recipeParsing(myEventList.get(0).getSuggestedRecipes());
        showText.setText("Recipe Title: " +mySRecipeList.get(0).getTitle());

       // showText.setText("This ist the ID: " +((CookooMainApplication) this.getApplication()).getGlobalEventList().get(0).getSuggestedRecipes());



    }
}
