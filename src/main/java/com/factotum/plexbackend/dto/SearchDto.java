package com.factotum.plexbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchDto {

    @JsonProperty("Search")
    private TitleDto[] search;

    @JsonProperty("totalResults")
    private int totalResults;

    @JsonProperty("Response")
    private boolean response;

    @JsonProperty("Error")
    private String error;

    public SearchDto() {
    }

    public TitleDto[] getSearch() {
        return search;
    }

    public void setSearch(TitleDto[] search) {
        this.search = search;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public boolean responseReturned() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
