package com.appspot.mycookooapp.api;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by ugurgul on 09/11/2016.
 */

@Entity
public class User {
    @Id
    private long id;
    private String name;
    private String password;
    private String homeLocation;
    private String friends;
    private long phoneNumber;
    private String diet;
    private String intolerances;
    private String excludedIngredients;
    private String alergy;
    private String photo;
    private String ingredientHistory;

    public User() {}

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getIntolerances() {
        return intolerances;
    }

    public void setIntolerances(String intolerances) {
        this.intolerances = intolerances;
    }

    public String getExcludedIngredients() {
        return excludedIngredients;
    }

    public void setExcludedIngredients(String excludedIngredients) {
        this.excludedIngredients = excludedIngredients;
    }

    public String getAlergy() {
        return alergy;
    }

    public void setAlergy(String alergy) {
        this.alergy = alergy;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIngredientHistory() {
        return ingredientHistory;
    }

    public void setIngredientHistory(String ingredientHistory) {
        this.ingredientHistory = ingredientHistory;
    }
}


