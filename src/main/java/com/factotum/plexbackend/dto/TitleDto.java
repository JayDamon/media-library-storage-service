package com.factotum.plexbackend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TitleDto implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("imdbID")
    private String imdb;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Poster")
    private String posterUrl;

    @JsonProperty("orderAdded")
    private Integer orderAdded;

    public TitleDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Integer getOrderAdded() {
        return orderAdded;
    }

    public void setOrderAdded(Integer orderAdded) {
        this.orderAdded = orderAdded;
    }

    @Override
    public String toString() {
        return "TitleDto{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbId='" + imdb + '\'' +
                ", type='" + type + '\'' +
                ", posterUrl=" + posterUrl +
                '}';
    }
}
