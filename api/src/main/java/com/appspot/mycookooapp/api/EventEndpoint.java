package com.appspot.mycookooapp.api;

/**
 * Created by ugurgul on 03/01/2017.
 */

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.gson.Gson;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import static com.appspot.mycookooapp.api.OfyServiceEvent.ofyEvent;

@Api(name = "eventEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.mycookooapp.appspot.com", ownerName = "api.mycookooapp.appspot.com", packagePath=""))


public class EventEndpoint {

    private static final Logger LOG = Logger.getLogger(EventEndpoint.class.getName());

    public EventEndpoint() {
    }

    /**
     * Return a collection of events
     *
     * @param count The number of events
     * @return a list of events
     */
    @ApiMethod(name = "listEvents")
    public CollectionResponse<Event> listEvent(@Nullable @Named("cursor") String cursorString,
                                               @Nullable @Named("count") Integer count) {
        Query<Event> query = ofyEvent().load().type(Event.class);
        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }
        List<Event> records = new ArrayList<Event>();
        QueryResultIterator<Event> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }
        //Find the next cursor
        if (cursorString != null && cursorString != "") {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<Event>builder().setItems(records).setNextPageToken(cursorString).build();
    }

    /**
     * This inserts a new <code>Event</code> object.
     * @param event The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertEvent")
    public Event insertEvent(Event event) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        if (findEventRecord(event.getId()) != null) {
            throw new ConflictException("Event Record already exists");
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put

        List<SuggestedRecipe> myRecipes = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex" +
                    "?addRecipeInformation=true" +
                    "&diet=" + event.getDiet() +
                    "&excludeIngredients=" + event.getExcludedIngredients() +
                    "&fillIngredients=false" +
                    "&includeIngredients=" + event.getIngredients() +
                    "&intolerances=" + event.getIntolerances() +
                    "&limitLicense=false" +
                    "&minCalories=0" +
                    "&number=5" +
                    "&offset=0" +
                    "&query=" + event.getTitle() +
                    "&ranking=1");
            getRequest.addHeader("X-Mashape-Key", "F96RTlcrSdmshs1NJWrP3p4pfUJGp1EzZHVjsnpK8suHjWHNN0");
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            String output;
            myRecipes = new ArrayList<>();

            while ((output = br.readLine()) != null) {

                JSONArray results;
                JSONObject node = new JSONObject(output);
                results = node.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    final JSONObject recipe = results.getJSONObject(i);
                    myRecipes.add(new SuggestedRecipe(recipe.getBoolean("vegetarian"), recipe.getBoolean("vegan"), recipe.getBoolean("glutenFree"), recipe.getBoolean("dairyFree"), recipe.getLong("id"), recipe.getString("title"), recipe.getInt("readyInMinutes"), recipe.getString("image"), recipe.getString("imageType"), recipe.getInt("usedIngredientCount"), recipe.getInt("missedIngredientCount"), recipe.getInt("calories")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        event.setSuggestedRecipes(new Gson().toJson(myRecipes));

        ofyEvent().save().entity(event).now();
        return event;
    }

    /**
     * This updates an existing <code>Event</code> object.
     * @param event The object to be added.
     * @return The object to be updated.
     */
    @ApiMethod(name = "updateEvent")
    public Event updateEvent(Event event)throws NotFoundException {
        if (findEventRecord(event.getId()) == null) {
            throw new NotFoundException("Event Record does not exist");
        }

        List<SuggestedRecipe> myRecipes = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex" +
                    "?addRecipeInformation=true" +
                    "&diet=" + event.getDiet() +
                    "&excludeIngredients=" + event.getExcludedIngredients() +
                    "&fillIngredients=false" +
                    "&includeIngredients=" + event.getIngredients() +
                    "&intolerances=" + event.getIntolerances() +
                    "&limitLicense=false" +
                    "&minCalories=0" +
                    "&number=5" +
                    "&offset=0" +
                    "&query=" + event.getTitle() +
                    "&ranking=1");
            getRequest.addHeader("X-Mashape-Key", "F96RTlcrSdmshs1NJWrP3p4pfUJGp1EzZHVjsnpK8suHjWHNN0");
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            String output;
            myRecipes = new ArrayList<>();

            while ((output = br.readLine()) != null) {

                JSONArray results;
                JSONObject node = new JSONObject(output);
                results = node.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    final JSONObject recipe = results.getJSONObject(i);
                    myRecipes.add(new SuggestedRecipe(recipe.getBoolean("vegetarian"), recipe.getBoolean("vegan"), recipe.getBoolean("glutenFree"), recipe.getBoolean("dairyFree"), recipe.getLong("id"), recipe.getString("title"), recipe.getInt("readyInMinutes"), recipe.getString("image"), recipe.getString("imageType"), recipe.getInt("usedIngredientCount"), recipe.getInt("missedIngredientCount"), recipe.getInt("calories")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        event.setSuggestedRecipes(new Gson().toJson(myRecipes));
        ofyEvent().save().entity(event).now();
        return event;
    }
    /**
     * This deletes an existing <code>Event</code> object.
     * @param id The id of the object to be deleted.
     */
    @ApiMethod(name = "removeEvent")
    public void removeEvent(@Named("id") Long id) throws NotFoundException {
        Event record = findEventRecord(id);
        if(record == null) {
            throw new NotFoundException("Event Record does not exist");
        }
        ofyEvent().delete().entity(record).now();
    }
    /**
     * This deletes all existing <code>Event</code> objects.
     */
    @ApiMethod(name = "removeAllEvents")
    public void removeAllEvents()  {

        List<Key<Event>> records = ofyEvent().load().type(Event.class).keys().list();
        ofyEvent().delete().keys(records).now();
    }

    //Private method to retrieve a <code>Quote</code> record
    private Event findEventRecord(Long id) {
        return ofyEvent().load().type(Event.class).id(id).now();
    }


}
