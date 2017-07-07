
package com.tonymontes.comicvine;

/*
  Created by tony on 2/13/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Character {

    @SerializedName("aliases")
    @Expose
    private Object aliases;
    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("birth")
    @Expose
    private Thing birth;
    @SerializedName("character_enemies")
    @Expose
    private List<Thing> characterEnemies = null;
    @SerializedName("character_friends")
    @Expose
    private List<Thing> characterFriends = null;
    @SerializedName("count_of_issue_appearances")
    @Expose
    private Integer countOfIssueAppearances;
    @SerializedName("creators")
    @Expose
    private List<Thing> creators = null;
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
    @SerializedName("first_appeared_in_issue")
    @Expose
    private Issue firstAppearedInIssue;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("issue_credits")
    @Expose
    private List<Issue> issueCredits = null;
    @SerializedName("issues_died_in")
    @Expose
    private List<Issue> issuesDiedIn = null;
    @SerializedName("movies")
    @Expose
    private List<Thing> movies = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("origin")
    @Expose
    private Origin origin;
    @SerializedName("powers")
    @Expose
    private List<Thing> powers = null;
    @SerializedName("publisher")
    @Expose
    private Publisher publisher;
    @SerializedName("real_name")
    @Expose
    private String realName;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;
    @SerializedName("story_arc_credits")
    @Expose
    private List<Storyarc> storyArcCredits = null;
    @SerializedName("team_enemies")
    @Expose
    private List<Thing> teamEnemies = null;
    @SerializedName("team_friends")
    @Expose
    private List<Thing> teamFriends = null;
    @SerializedName("teams")
    @Expose
    private List<Thing> teams = null;
    @SerializedName("volume_credits")
    @Expose
    private List<Volume> volumeCredits = null;

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

    public List<Thing> getCharacterEnemies() {
        return characterEnemies;
    }

    public void setCharacterEnemies(List<Thing> characterEnemies) {
        this.characterEnemies = characterEnemies;
    }

    public List<Thing> getCharacterFriends() {
        return characterFriends;
    }

    public void setCharacterFriends(List<Thing> characterFriends) {
        this.characterFriends = characterFriends;
    }

    public Integer getCountOfIssueAppearances() {
        return countOfIssueAppearances;
    }

    public void setCountOfIssueAppearances(Integer countOfIssueAppearances) {
        this.countOfIssueAppearances = countOfIssueAppearances;
    }

    public List<Thing> getCreators() {
        return creators;
    }

    public void setCreators(List<Thing> creators) {
        this.creators = creators;
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

    public Issue getFirstAppearedInIssue() {
        return firstAppearedInIssue;
    }

    public void setFirstAppearedInIssue(Issue firstAppearedInIssue) {
        this.firstAppearedInIssue = firstAppearedInIssue;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public List<Issue> getIssueCredits() {
        return issueCredits;
    }

    public void setIssueCredits(List<Issue> issueCredits) {
        this.issueCredits = issueCredits;
    }

    public List<Issue> getIssuesDiedIn() {
        return issuesDiedIn;
    }

    public void setIssuesDiedIn(List<Issue> issuesDiedIn) {
        this.issuesDiedIn = issuesDiedIn;
    }

    public List<Thing> getMovies() {
        return movies;
    }

    public void setMovies(List<Thing> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public List<Thing> getPowers() {
        return powers;
    }

    public void setPowers(List<Thing> powers) {
        this.powers = powers;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public List<Thing> getTeamEnemies() {
        return teamEnemies;
    }

    public void setTeamEnemies(List<Thing> teamEnemies) {
        this.teamEnemies = teamEnemies;
    }

    public List<Thing> getTeamFriends() {
        return teamFriends;
    }

    public void setTeamFriends(List<Thing> teamFriends) {
        this.teamFriends = teamFriends;
    }

    public List<Thing> getTeams() {
        return teams;
    }

    public void setTeams(List<Thing> teams) {
        this.teams = teams;
    }

    public List<Volume> getVolumeCredits() {
        return volumeCredits;
    }

    public void setVolumeCredits(List<Volume> volumeCredits) {
        this.volumeCredits = volumeCredits;
    }
}
