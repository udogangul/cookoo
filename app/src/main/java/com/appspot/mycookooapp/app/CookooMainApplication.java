package com.appspot.mycookooapp.app;

import android.app.Application;
import com.appspot.mycookooapp.api.eventEndpoint.model.Event;

import java.util.List;

/**
 * Created by Tuti on 02.01.17.
 */

public class CookooMainApplication extends Application {


    private String userName = "Tuti";
    private int userID = 2;
    private List<Event> globalEventList = null;


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("CookooMain has been started");
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
