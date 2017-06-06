package com.appspot.mycookooapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RefreshAsyncTask(this).execute();
    }

    public void onClickNewEvent(View v) {
        Intent intent = new Intent(this, NewEventActivity.class);
        startActivity(intent);
    }

    public void onClickShowRecipes(View v){
        Intent intent = new Intent(this, RecipesActivity.class);
        startActivity(intent);
    }

    public void onClickShowEvents(View v){
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }
}