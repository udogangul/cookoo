package com.appspot.mycookooapp.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        showText = (TextView)findViewById(R.id.textView2);
        showText.setText("Hello Ugur");

        showText.setText("This ist the ID: " +((CookooMainApplication) this.getApplication()).getGlobalEventList().get(0).getHostid());



    }
}
