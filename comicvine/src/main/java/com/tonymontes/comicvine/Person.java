
package com.tonymontes.comicvine;

/*
  Created by tony on 2/13/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Person {

    @SerializedName("aliases")
    @Expose
    private Object aliases;
    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("birth")
    @Expose
    private Thing birth;
    @SerializedName("count_of_isssue_appearances")
    @Expose
    private Integer countOfIsssueAppearances;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("created_characters")
    @Expose
    private List<Character> createdCharacters = null;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("date_last_updated")
    @Expose
    private String dateLastUpdated;
    @SerializedName("death")
    @Expose
    private Thing death;
    @SerializedName("deck")
    @Expose
    private String deck;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("email")
    @Expose
    private Thing email;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("hometown")
    @Expose
    private String hometown;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("issues")
    @Expose
    private List<Issue> issues = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;
    @SerializedName("story_arc_credits")
    @Expose
    private List<Storyarc> storyArcCredits = null;
    @SerializedName("volume_credits")
    @Expose
    private List<Volume> volumeCredits = null;
    @SerializedName("website")
    @Expose
    private String website;

    public Object getAliases() {
        return aliases;
    }

    public void setAliases(Object aliases) {
        this.aliases = aliases;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
    }

    public Thing getBirth() {
        return birth;
    }

    public void setBirth(Thing birth) {
        this.birth = birth;
    }

    public Integer getCountOfIsssueAppearances() {
        return countOfIsssueAppearances;
    }

    public void setCountOfIsssueAppearances(Integer countOfIsssueAppearances) {
        this.countOfIsssueAppearances = countOfIsssueAppearances;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Character> getCreatedCharacters() {
        return createdCharacters;
    }

    public void setCreatedCharacters(List<Character> createdCharacters) {
        this.createdCharacters = createdCharacters;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(String dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public Thing getDeath() {
        return death;
    }

    public void setDeath(Thing death) {
        this.death = death;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thing getEmail() {
        return email;
    }

    public void setEmail(Thing email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public List<Storyarc> getStoryArcCredits() {
        return storyArcCredits;
    }

    public void setStoryArcCredits(List<Storyarc> storyArcCredits) {
        this.storyArcCredits = storyArcCredits;
    }

    public List<Volume> getVolumeCredits() {
        return volumeCredits;
    }

    public void setVolumeCredits(List<Volume> volumeCredits) {
        this.volumeCredits = volumeCredits;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
