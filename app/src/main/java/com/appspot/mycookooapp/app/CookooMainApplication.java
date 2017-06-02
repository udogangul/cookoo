package com.appspot.mycookooapp.app;

import android.app.Application;

import com.appspot.mycookooapp.api.eventEndpoint.EventEndpoint;
import com.appspot.mycookooapp.api.eventEndpoint.model.Event;

import java.util.List;

/**
 * Created by Tuti on 02.01.17.
 */

public class CookooMainApplication extends Application {


    private String userName;
    private int userID;
    private List<Event> globalEventList;
    int callId = 1;
    EventEndpoint myApiService = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String name){
        userName = name;
    }

    public int getUserID(){
        return userID;
    }

    public void setUserID(int id){
        userID = id;
    }

    public void setGlobalEventList(List<Event> inputEventList){
        globalEventList = inputEventList;
    }
    public List<Event> getGlobalEventList(){
        return globalEventList;
    }

}
