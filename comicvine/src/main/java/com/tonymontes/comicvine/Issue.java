
package com.tonymontes.comicvine;

/*
  Created by tony on 2/13/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Issue {

    @SerializedName("aliases")
    @Expose
    private Thing aliases;
    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("character_credits")
    @Expose
    private List<Character> characterCredits = null;
    @SerializedName("character_died_in")
    @Expose
    private List<Thing> characterDiedIn = null;
    @SerializedName("concept_credits")
    @Expose
    private List<Concept> conceptCredits = null;
    @SerializedName("cover_date")
    @Expose
    private String coverDate;
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
    @SerializedName("first_appearance_characters")
    @Expose
    private List<Character> firstAppearanceCharacters = null;
    @SerializedName("first_appearance_concepts")
    @Expose
    private List<Concept> firstAppearanceConcepts;
    @SerializedName("first_appearance_locations")
    @Expose
    private List<Location> firstAppearanceLocations;
    @SerializedName("first_appearance_objects")
    @Expose
    private List<Thing> firstAppearanceObjects;
    @SerializedName("first_appearance_storyarcs")
    @Expose
    private List<Storyarc> firstAppearanceStoryarcs = null;
    @SerializedName("first_appearance_teams")
    @Expose
    private List<Team> firstAppearanceTeams;
    @SerializedName("has_staff_review")
    @Expose
    private Boolean hasStaffReview;
    @SerializedName("id")
    @Expose
    private Integer comicvineID;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("issue_number")
    @Expose
    private String issueNumber;
    @SerializedName("location_credits")
    @Expose
    private List<Location> locationCredits = null;
    @SerializedName("name")
    @Expose
    private String title;
    @SerializedName("object_credits")
    @Expose
    private List<Thing> objectCredits = null;
    @SerializedName("person_credits")
    @Expose
    private List<Person> personCredits = null;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;
    @SerializedName("store_date")
    @Expose
    private String storeDate;
    @SerializedName("story_arc_credits")
    @Expose
    private List<Storyarc> storyArcCredits = null;
    @SerializedName("team_credits")
    @Expose
    private List<Team> teamCredits = null;
    @SerializedName("team_disbanded_in")
    @Expose
    private List<Team> teamDisbandedIn = null;
    @SerializedName("volume")
    @Expose
    private Volume volume;

    public Thing getAliases() {
        return aliases;
    }

    public void setAliases(Thing aliases) {
        this.aliases = aliases;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
    }

    public List<Character> getCharacterCredits() {
        return characterCredits;
    }

    public void setCharacterCredits(List<Character> characterCredits) {
        this.characterCredits = characterCredits;
    }

    public List<Thing> getCharacterDiedIn() {
        return characterDiedIn;
    }

    public void setCharacterDiedIn(List<Thing> characterDiedIn) {
        this.characterDiedIn = characterDiedIn;
    }

    public List<Concept> getConceptCredits() {
        return conceptCredits;
    }

    public void setConceptCredits(List<Concept> conceptCredits) {
        this.conceptCredits = conceptCredits;
    }

    public String getCoverDate() {
        return coverDate;
    }

    public void setCoverDate(String coverDate) {
        this.coverDate = coverDate;
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

    public List<Character> getFirstAppearanceCharacters() {
        return firstAppearanceCharacters;
    }

    public void setFirstAppearanceCharacters(List<Character> firstAppearanceCharacters) {
        this.firstAppearanceCharacters = firstAppearanceCharacters;
    }

    public List<Concept> getFirstAppearanceConcepts() {
        return firstAppearanceConcepts;
    }

    public void setFirstAppearanceConcepts(List<Concept> firstAppearanceConcepts) {
        this.firstAppearanceConcepts = firstAppearanceConcepts;
    }

    public List<Location> getFirstAppearanceLocations() {
        return firstAppearanceLocations;
    }

    public void setFirstAppearanceLocations(List<Location> firstAppearanceLocations) {
        this.firstAppearanceLocations = firstAppearanceLocations;
    }

    public List<Thing> getFirstAppearanceObjects() {
        return firstAppearanceObjects;
    }

    public void setFirstAppearanceObjects(List<Thing> firstAppearanceObjects) {
        this.firstAppearanceObjects = firstAppearanceObjects;
    }

    public List<Storyarc> getFirstAppearanceStoryarcs() {
        return firstAppearanceStoryarcs;
    }

    public void setFirstAppearanceStoryarcs(List<Storyarc> firstAppearanceStoryarcs) {
        this.firstAppearanceStoryarcs = firstAppearanceStoryarcs;
    }

    public List<Team> getFirstAppearanceTeams() {
        return firstAppearanceTeams;
    }

    public void setFirstAppearanceTeams(List<Team> firstAppearanceTeams) {
        this.firstAppearanceTeams = firstAppearanceTeams;
    }

    public Boolean getHasStaffReview() {
        return hasStaffReview;
    }

    public void setHasStaffReview(Boolean hasStaffReview) {
        this.hasStaffReview = hasStaffReview;
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

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public List<Location> getLocationCredits() {
        return locationCredits;
    }

    public void setLocationCredits(List<Location> locationCredits) {
        this.locationCredits = locationCredits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Thing> getObjectCredits() {
        return objectCredits;
    }

    public void setObjectCredits(List<Thing> objectCredits) {
        this.objectCredits = objectCredits;
    }

    public List<Person> getPersonCredits() {
        return personCredits;
    }

    public void setPersonCredits(List<Person> personCredits) {
        this.personCredits = personCredits;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getStoreDate() {
        return storeDate;
    }

    public void setStoreDate(String storeDate) {
        this.storeDate = storeDate;
    }

    public List<Storyarc> getStoryArcCredits() {
        return storyArcCredits;
    }

    public void setStoryArcCredits(List<Storyarc> storyArcCredits) {
        this.storyArcCredits = storyArcCredits;
    }

    public List<Team> getTeamCredits() {
        return teamCredits;
    }

    public void setTeamCredits(List<Team> teamCredits) {
        this.teamCredits = teamCredits;
    }

    public List<Team> getTeamDisbandedIn() {
        return teamDisbandedIn;
    }

    public void setTeamDisbandedIn(List<Team> teamDisbandedIn) {
        this.teamDisbandedIn = teamDisbandedIn;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }
}
