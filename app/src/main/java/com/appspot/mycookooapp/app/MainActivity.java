package com.appspot.mycookooapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        new RefreshAsyncTask(this).execute();
    }


    public void onClickNext(View v){
        System.out.println("hello this a click");
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);

    }

}

