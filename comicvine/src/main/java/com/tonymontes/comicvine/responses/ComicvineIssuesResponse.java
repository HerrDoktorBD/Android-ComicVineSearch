
package com.tonymontes.comicvine.responses;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tonymontes.comicvine.Issue;

import java.io.IOException;
import java.util.List;

public class ComicvineIssuesResponse {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("number_of_page_results")
    @Expose
    private Integer numberOfPageResults;
    @SerializedName("number_of_total_results")
    @Expose
    private Integer numberOfTotalResults;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("results")
    @Expose
    private List<Issue> results = null;
    @SerializedName("version")
    @Expose
    private String version;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumberOfPageResults() {
        return numberOfPageResults;
    }

    public void setNumberOfPageResults(Integer numberOfPageResults) {
        this.numberOfPageResults = numberOfPageResults;
    }

    public Integer getNumberOfTotalResults() {
        return numberOfTotalResults;
    }

    public void setNumberOfTotalResults(Integer numberOfTotalResults) {
        this.numberOfTotalResults = numberOfTotalResults;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Issue> getResults() {
        return results;
    }

    public void setResults(List<Issue> results) {
        this.results = results;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    // generated at http://www.jsonschema2pojo.org/ with source type JSON and annotation style GSON with JSON from:
    // "http://comicvine.gamespot.com/api/issues/?api_key=YOUR-KEY&format=json&sort=issue_number:asc&filter=volume:21117"
    public static ComicvineIssuesResponse parseJSON(String json) throws IOException {

        try {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(json, ComicvineIssuesResponse.class);
        } catch (JsonParseException jpe) {
            Log.w("ComicvineIssuesResponse", jpe);
            throw new IOException("JSON Parse Exception");
        }
    }
}
