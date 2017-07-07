
package com.tonymontes.comicvine;

/*
  Created by tony on 2/13/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Volume {

    @SerializedName("aliases")
    @Expose
    private Object aliases;
    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("characters")
    @Expose
    private List<Character> characters = null;
    @SerializedName("concepts")
    @Expose
    private List<Concept> concepts = null;
    @SerializedName("count_of_issues")
    @Expose
    private Integer countOfIssues;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("date_last_updated")
    @Expose
    private String dateLastUpdated;
    @SerializedName("deck")
    @Expose
    private String deck;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("first_issue")
    @Expose
    private Issue firstIssue;
    @SerializedName("id")
    @Expose
    private Integer comicvineID;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("issues")
    @Expose
    private List<Issue> issues = null;
    @SerializedName("last_issue")
    @Expose
    private Issue lastIssue;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("name")
    @Expose
    private String title;
    @SerializedName("objects")
    @Expose
    private List<Thing> objects = null;
    @SerializedName("people")
    @Expose
    private List<Person> people = null;
    @SerializedName("publisher")
    @Expose
    private Publisher publisher;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;
    @SerializedName("start_year")
    @Expose
    private String startYear;

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

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Concept> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<Concept> concepts) {
        this.concepts = concepts;
    }

    public Integer getCountOfIssues() {
        return countOfIssues;
    }

    public void setCountOfIssues(Integer countOfIssues) {
        this.countOfIssues = countOfIssues;
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

    public Issue getFirstIssue() {
        return firstIssue;
    }

    public void setFirstIssue(Issue firstIssue) {
        this.firstIssue = firstIssue;
    }

    public Integer getComicvineID() {
        return comicvineID;
    }

    public void setComicvineID(Integer comicvineID) {
        this.comicvineID = comicvineID;
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

    public Issue getLastIssue() {
        return lastIssue;
    }

    public void setLastIssue(Issue lastIssue) {
        this.lastIssue = lastIssue;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }

    public List<Thing> getObjects() {
        return objects;
    }

    public void setObjects(List<Thing> objects) {
        this.objects = objects;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }
}
