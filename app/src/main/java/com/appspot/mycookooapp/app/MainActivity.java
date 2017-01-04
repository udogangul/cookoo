package com.appspot.mycookooapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.appspot.mycookooapp.api.eventEndpoint.model.Event;
public class MainActivity extends AppCompatActivity {


    String test = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void getEvents(View v) {
        //new EndpointsAsyncTask(this).execute();

        System.out.println("This is the name: " +((CookooMainApplication) this.getApplication()).getUserName());

        Event myEvent = new Event();
        myEvent.setId((long)1);
        myEvent.setHostid("Tuti");
        myEvent.setTitle("soup");
        myEvent.setDiet("vegan");
        myEvent.setIngredients("beef");
        myEvent.setExcludedIngredients("chicken");
        myEvent.setIntolerances("lactose");


      //  new InsertEventAsyncTask(this, myEvent).execute();
        new RefreshAsyncTask(this).execute();
    }


    public void onClickNext(View v){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);

    }

}

