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
import java.util.List;

/**
 * Created by Tuti on 04.01.17.
 */

public class InsertAsyncTask extends AsyncTask<Event, Void, List<Event>> {


    private static EventEndpoint myApiService = null;
    private Context context;

   // InsertAsyncTask(Context context) {
   //     this.context = context;
   // }

    @Override
    protected List<Event> doInBackground(Event... myEvent) {
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

            myApiService.insertEvent(myEvent[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return myApiService.listEvents().execute().getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


        @Override
        protected void onPostExecute(List<Event> result) {
            ((CookooMainApplication)((MainActivity) context).getApplication()).setGlobalEventList(result);
        }
}

