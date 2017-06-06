package com.appspot.mycookooapp.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appspot.mycookooapp.api.eventEndpoint.model.Event;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by ugurgul on 02.06.17.
 */

public class NewEventActivity extends AppCompatActivity {

    EditText eventTitleText;
    EditText eventDateText;
    EditText eventIngredientsText;
    Button insertEvent;
    Event newEvent = new Event();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newevent);

        insertEvent = (Button) findViewById(R.id.btnInsertEvent);
        eventTitleText = (EditText) findViewById(R.id.eventTitle);
        eventDateText = (EditText) findViewById(R.id.eventDate);
        eventIngredientsText = (EditText) findViewById(R.id.eventIngredients);

    }

    @Override
    protected void onStart() {
        super.onStart();
        newEvent.setHostid("Ugur");
        newEvent.setDiet("");
        newEvent.setExcludedIngredients("");
        newEvent.setIntolerances("");
        newEvent.setLocation("Haidhausen");

    }
    @Override
    protected void onResume() {
        super.onResume();
        List<Event> myEventList = null;
        try {
            myEventList = new RefreshAsyncTask(this).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        final List<Event> finalMyEventList = myEventList;
        insertEvent.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if (finalMyEventList==null) {
                            newEvent.setId((long)1);
                        }
                        else {
                            newEvent.setId(finalMyEventList.get(finalMyEventList.size()-1).getId() + (long) 1);
                        }
                    newEvent.setTitle(eventTitleText.getText().toString());
                    newEvent.setTime(eventDateText.getText().toString());
                    newEvent.setIngredients(eventIngredientsText.getText().toString());
                    new InsertAsyncTask(context).execute(newEvent);
                    insertEvent.setText("Event added");
                    }
                });
    }

    }
