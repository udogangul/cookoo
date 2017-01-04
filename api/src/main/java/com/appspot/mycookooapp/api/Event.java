package com.appspot.mycookooapp.api;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by ugurgul on 09/11/2016.
 */

@Entity
public class Event {
    @Id
    private long id;
    private String hostid;
    private String title;
    private String time;
    private String ingredients;
    private String missingIngredients;
    private String invitedGuests;
    private String acceptedUsers;
    private String location;
    private String diet;
    private String intolerances;
    private String excludedIngredients;
    private String alergy;
    private String suggestedRecipes;
    private String selectedRecipes;
    private String costPerGuest;
    private long maxNoOfGuests;
    private String timeout;
    private String kind;

    public Event() {}

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMissingIngredients() {
        return missingIngredients;
    }

    public void setMissingIngredients(String missingIngredients) {
        this.missingIngredients = missingIngredients;
    }

    public String getInvitedGuests() {
        return invitedGuests;
    }

    public void setInvitedGuests(String invitedGuests) {
        this.invitedGuests = invitedGuests;
    }

    public String getAcceptedUsers() {
        return acceptedUsers;
    }

    public void setAcceptedUsers(String acceptedUsers) {
        this.acceptedUsers = acceptedUsers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getSuggestedRecipes() {
        return suggestedRecipes;
    }

    public void setSuggestedRecipes(String suggestedRecipes) {
        this.suggestedRecipes = suggestedRecipes;
    }

    public String getSelectedRecipes() {
        return selectedRecipes;
    }

    public void setSelectedRecipes(String selectedRecipes) {
        this.selectedRecipes = selectedRecipes;
    }

    public String getCostPerGuest() {
        return costPerGuest;
    }

    public void setCostPerGuest(String costPerGuest) {
        this.costPerGuest = costPerGuest;
    }

    public long getMaxNoOfGuests() {
        return maxNoOfGuests;
    }

    public void setMaxNoOfGuests(int maxNoOfGuests) {
        this.maxNoOfGuests = maxNoOfGuests;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
