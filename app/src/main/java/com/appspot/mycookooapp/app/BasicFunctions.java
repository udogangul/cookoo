package com.appspot.mycookooapp.app;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Tuti on 04.01.17.
 */

public class BasicFunctions {


    public BasicFunctions(){

    }


    public List<SuggestedRecipe> recipeParsing(String recipes_in){
        Gson gson = new Gson();
        List<SuggestedRecipe> myRecipeList = null;
       return myRecipeList = gson.fromJson(recipes_in, new TypeToken<List<SuggestedRecipe>>(){}.getType());

    }
}
