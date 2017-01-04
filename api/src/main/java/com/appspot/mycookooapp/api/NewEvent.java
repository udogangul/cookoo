package com.appspot.mycookooapp.api;

/**
 * Created by ugurgul on 09/11/2016.
 */

public class NewEvent {
    private long id;
    private String hostid;
    private String title;
    private long maxNoOfGuests;


    public NewEvent() {}


    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) { this.hostid = hostid; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getMaxNoOfGuests() {
        return maxNoOfGuests;
    }

    public void setMaxNoOfGuests(int maxNoOfGuests) {
        this.maxNoOfGuests = maxNoOfGuests;
    }


}
