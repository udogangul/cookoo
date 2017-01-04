package com.appspot.mycookooapp.api;

/**
 * Created by ugurgul on 13/11/2016.
 */

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Objectify service wrapper so we can statically register our persistence classes
 * More on Objectify here : https://code.google.com/p/objectify-appengine/
 *
 */
public class OfyServiceEvent {
    static {
        ObjectifyService.register(Event.class);
    }
    public static Objectify ofyEvent() {
        return ObjectifyService.ofy();
    }
    public static ObjectifyFactory factoryEvent() {
        return ObjectifyService.factory();
    }
}
