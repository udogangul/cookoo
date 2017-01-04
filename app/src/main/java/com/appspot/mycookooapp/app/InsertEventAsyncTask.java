package com.appspot.mycookooapp.app;

import android.content.Context;
import android.os.AsyncTask;

import com.appspot.mycookooapp.api.eventEndpoint.EventEndpoint;
import com.appspot.mycookooapp.api.eventEndpoint.model.Event;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Tuti on 04.01.17.
 */

public class InsertEventAsyncTask extends AsyncTask<Object, Object, Void> {

    private static EventEndpoint myApiService = null;
    private Context context;
    private Event event_sending;

    //private CookooMainApplication cka;

    InsertEventAsyncTask(Context context, Event event) {
        this.context = context;
        event_sending = event;

    }

    @Override
    protected Void doInBackground(Object... params) {
        if (myApiService == null) {  // Only do this once
            EventEndpoint.Builder builder = new EventEndpoint.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://mycookooapp.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            myApiService.insertEvent(event_sending).execute();

        } catch (IOException e) {

        }
        return null;
    }


}
