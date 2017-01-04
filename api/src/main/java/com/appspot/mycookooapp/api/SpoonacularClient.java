package com.appspot.mycookooapp.api;

/**
 * Created by ugurgul on 22/10/2016.
 */

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SpoonacularClient {

    public static String findSpoonacularRecipe(String diet, String excludeIngredients, String includeIngredients, String intolerances, String query)
    {
        List<SuggestedRecipe> myRecipes = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            diet = diet.replaceAll("%2C", ",");
            excludeIngredients = excludeIngredients.replaceAll("%2C", ",");
            includeIngredients = includeIngredients.replaceAll("%2C", ",");
            intolerances = intolerances.replaceAll("%2C", ",");
            query = query.replaceAll("%2C", ",");

            HttpGet getRequest = new HttpGet("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex" +
                    "?addRecipeInformation=true" +
                    "&diet=" + diet +
                    "&excludeIngredients=" + excludeIngredients +
                    "&fillIngredients=false" +
                    "&includeIngredients=" + includeIngredients +
                    "&intolerances=" + intolerances +
                    "&limitLicense=false" +
                    "&minCalories=0" +
                    "&number=5" +
                    "&offset=0" +
                    "&query=" + query +
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
        return new Gson().toJson(myRecipes);
    }
    public static void main (String[] args)
    {
        try {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url="https://mycookooapp.appspot.com/_ah/api/myApi/v1/event";
        HttpPost post = new HttpPost(url);
        NewEvent myevent = new NewEvent();
        myevent.setId(10);
        myevent.setHostid("who");
        myevent.setMaxNoOfGuests(666);
        myevent.setTitle("best meal");
        Gson gson1 = new Gson();
        StringEntity postingString = new StringEntity(gson1.toJson(myevent));//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        httpClient.execute(post);
        } catch (IOException ex) {
        }
    }
}
         /*

         //code for getting suggested recipes
         try {
         CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         String url="https://mycookooapp.appspot.com/_ah/api/myApi/v1/ingredientsearch/vegan/beef/potato,zuccini,eggplant/lactose/soup";
         HttpPost request = new HttpPost(url);
         request.addHeader("content-type", "application/json");
         HttpResponse result = httpClient.execute(request);
         String json = EntityUtils.toString(result.getEntity(), "UTF-8");

         List<SuggestedRecipe> myRecipe = null;
         Gson gson = new Gson();
         ResponseHandler resp = gson.fromJson(json, ResponseHandler.class);
         myRecipe = gson.fromJson(resp.getMyData(), new TypeToken<List<SuggestedRecipe>>() {}.getType());
         for (int i = 0; i < myRecipe.size(); i++)
             System.out.println(myRecipe.get(i).getTitle());
             } catch (IOException ex) {
        }

             // code for reading events
        try {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url="https://mycookooapp.appspot.com/_ah/api/myApi/v1/event";
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        HttpResponse result = httpClient.execute(request);
        String json = EntityUtils.toString(result.getEntity(), "UTF-8");
        Gson gson = new Gson();
        ResponseHandlerEvent resp = gson.fromJson(json, ResponseHandlerEvent.class);
        for (int i = 0; i < resp.getEvents().size(); i++)
            System.out.println(resp.getEvents().get(i).getTitle());
        } catch (IOException ex) {
        }

        // code for creating a new event
        try {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url="https://mycookooapp.appspot.com/_ah/api/myApi/v1/event";
        HttpPost post = new HttpPost(url);
        NewEvent myevent = new NewEvent();
        myevent.setId(10);
        myevent.setHostid("who");
        myevent.setMaxNoOfGuests(666);
        myevent.setTitle("best meal");
        Gson gson1 = new Gson();
        StringEntity postingString = new StringEntity(gson1.toJson(myevent));//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        httpClient.execute(post);
        } catch (IOException ex) {
        }
*/

