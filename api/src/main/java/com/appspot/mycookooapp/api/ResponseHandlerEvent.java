package com.appspot.mycookooapp.api;

import java.util.List;

/**
 * Created by ugurgul on 30/12/2016.
 */

public class ResponseHandlerEvent {

    private List<NewEvent> items;
    private String kind;
    private String etag;

    public ResponseHandlerEvent() {
    }

    public List<NewEvent> getEvents() {
        return items;
    }

    public void setEvents(List<NewEvent> event) {
        this.items = event;
    }

}

