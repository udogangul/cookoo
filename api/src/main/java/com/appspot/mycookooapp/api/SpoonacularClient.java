package com.appspot.mycookooapp.api;

/**
 * Created by ugurgul on 22/10/2016.
 */
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.io.IOException;
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
                    myRecipes.add(new SuggestedRecipe(recipe.getBoolean("vegetarian"), recipe.getBoolean("vegan"), recipe.getBoolean("glutenFree"), recipe.getBoolean("dairyFree"), recipe.getDouble("id"), recipe.getString("title"), recipe.getInt("readyInMinutes"), recipe.getString("image"), recipe.getString("imageType"), recipe.getInt("usedIngredientCount"), recipe.getInt("missedIngredientCount"), recipe.getInt("calories")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().toJson(myRecipes);
    }
}


