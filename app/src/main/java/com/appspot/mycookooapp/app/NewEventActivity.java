package com.appspot.mycookooapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appspot.mycookooapp.api.eventEndpoint.model.Event;

import java.util.List;

/**
 * Created by ugurgul on 02.06.17.
 */

public class NewEventActivity extends AppCompatActivity {

    EditText eventTitleText;
    EditText eventDateText;
    EditText eventIngredientsText;
    Button insertEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newevent);

        List<Event> myEventList = ((CookooMainApplication) this.getApplication()).getGlobalEventList();

        long newId=myEventList.size()+1;

        insertEvent = (Button)findViewById(R.id.btnInsertEvent);
        eventTitleText = (EditText)findViewById(R.id.eventTitle);
        eventDateText = (EditText)findViewById(R.id.eventDate);
        eventIngredientsText = (EditText)findViewById(R.id.eventIngredients);
        final Event newEvent = new Event();

        newEvent.setId((newId));
        newEvent.setHostid("Ugur");
        newEvent.setDiet("paleo");
        newEvent.setExcludedIngredients("onion");
        newEvent.setIntolerances("dairy");
        newEvent.setLocation("Haidhausen");
        insertEvent.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                        newEvent.setTitle(eventTitleText.getText().toString());
                        newEvent.setTime(eventDateText.getText().toString());
                        newEvent.setIngredients(eventIngredientsText.getText().toString());
                        new InsertAsyncTask().execute(newEvent);
                    }
                });
    }
    }
