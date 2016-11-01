/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.appspot.mycookooapp.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.List;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "api.ugurgul.cookoo.com",
                ownerName = "api.ugurgul.cookoo.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "ingredientsearch")
    public Data ingredientsearch(@Named("diet") String diet, @Named("excludeIngredients") String excludeIngredients, @Named("includeIngredients") String includeIngredients, @Named("intolerances") String intolerances, @Named("query") String query)
    {
        Data response = new Data();
        String recipes = SpoonacularClient.findSpoonacularRecipe(diet,excludeIngredients,includeIngredients,intolerances,query);
        response.setMyData(recipes);
        return response;
    }
}
