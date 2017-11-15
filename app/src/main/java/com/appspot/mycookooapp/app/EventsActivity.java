package com.appspot.mycookooapp.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.appspot.mycookooapp.api.eventEndpoint.model.Event;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EventsActivity extends AppCompatActivity {

    private TextView showHost1;
    private TextView showHost2;
    private TextView showHost3;
    private TextView showHost4;

    private TextView showTitle1;
    private TextView showTitle2;
    private TextView showTitle3;
    private TextView showTitle4;

    private TextView showDiet1;
    private TextView showDiet2;
    private TextView showDiet3;
    private TextView showDiet4;

    private TextView showLocation1;
    private TextView showLocation2;
    private TextView showLocation3;
    private TextView showLocation4;

    private TextView showTime1;
    private TextView showTime2;
    private TextView showTime3;
    private TextView showTime4;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        showHost1 = (TextView)findViewById(R.id.host1);
        showHost2 = (TextView)findViewById(R.id.host2);
        showHost3 = (TextView)findViewById(R.id.host3);
        showHost4 = (TextView)findViewById(R.id.host4);

        showTitle1 = (TextView)findViewById(R.id.title1);
        showTitle2 = (TextView)findViewById(R.id.title2);
        showTitle3 = (TextView)findViewById(R.id.title3);
        showTitle4 = (TextView)findViewById(R.id.title4);

        showDiet1 = (TextView)findViewById(R.id.diet1);
        showDiet2 = (TextView)findViewById(R.id.diet2);
        showDiet3 = (TextView)findViewById(R.id.diet3);
        showDiet4 = (TextView)findViewById(R.id.diet4);

        showLocation1 = (TextView)findViewById(R.id.location1);
        showLocation2 = (TextView)findViewById(R.id.location2);
        showLocation3 = (TextView)findViewById(R.id.location3);
        showLocation4 = (TextView)findViewById(R.id.location4);

        showTime1 = (TextView)findViewById(R.id.time1);
        showTime2 = (TextView)findViewById(R.id.time2);
        showTime3 = (TextView)findViewById(R.id.time3);
        showTime4 = (TextView)findViewById(R.id.time4);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Event> myEventList = null;
        try {
            myEventList = new RefreshAsyncTask(this).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (myEventList!=null) {
            int i = myEventList.size();
            if (i >= 4) {
                findViewById(R.id.imageView4).setVisibility(View.VISIBLE);
                showHost4.setText(myEventList.get(i - 4).getHostid());
                showTitle4.setText(myEventList.get(i - 4).getTitle());
                showDiet4.setText(myEventList.get(i - 4).getDiet());
                showLocation4.setText(myEventList.get(i - 4).getLocation());
                showTime4.setText(myEventList.get(i - 4).getTime());
            }
            if (i >= 3) {
                findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
                showHost3.setText(myEventList.get(i - 3).getHostid());
                showTitle3.setText(myEventList.get(i - 3).getTitle());
                showDiet3.setText(myEventList.get(i - 3).getDiet());
                showLocation3.setText(myEventList.get(i - 3).getLocation());
                showTime3.setText(myEventList.get(i - 3).getTime());
            }
            if (i >= 2) {
                findViewById(R.id.imageView2).setVisibility(View.VISIBLE);
                showHost2.setText(myEventList.get(i - 2).getHostid());
                showTitle2.setText(myEventList.get(i - 2).getTitle());
                showDiet2.setText(myEventList.get(i - 2).getDiet());
                showLocation2.setText(myEventList.get(i - 2).getLocation());
                showTime2.setText(myEventList.get(i - 2).getTime());
            }
            if (i >= 1) {
                findViewById(R.id.imageView1).setVisibility(View.VISIBLE);
                showHost1.setText(myEventList.get(i - 1).getHostid());
                showTitle1.setText(myEventList.get(i - 1).getTitle());
                showDiet1.setText(myEventList.get(i - 1).getDiet());
                showLocation1.setText(myEventList.get(i - 1).getLocation());
                showTime1.setText(myEventList.get(i - 1).getTime());
            }
        }
    }

    public void onClickShowRecipes(View v){

        switch (v.getId()) {
            case R.id.event1:
                ((CookooMainApplication) this.getApplication()).callId=0;
                break;
            case R.id.event2:
                ((CookooMainApplication) this.getApplication()).callId=1;
                break;
            case R.id.event3:
                ((CookooMainApplication) this.getApplication()).callId=2;
                break;
            case R.id.event4:
                ((CookooMainApplication) this.getApplication()).callId=3;
                break;
        }
        Intent intent = new Intent(this, RecipesActivity.class);
        startActivity(intent);
    }

    public void onClickNewEvent(View v) {
        Intent intent = new Intent(this, NewEventActivity.class);
        startActivity(intent);
    }
    public void onClickDeleteEvents(View v) {
        new DeleteAsyncTask(context).execute();
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);

    }
}
