package com.factotum.medialibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "media_title")
public class Title implements Serializable {

    @Id
    @Column(name = "media_title_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_name")
    private String title;

    @Column(name = "title_year")
    private String year;

    @Column(name = "imdb_id")
    private String imdb;

    @Column(name = "media_type")
    private String type;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "order_added")
    @OrderBy
    private Integer orderAdded;

    public Title() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setImdb(String imdbId) {
        this.imdb = imdbId;
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

    public void setPosterUrl(String url) {
        this.posterUrl = url;
    }

    public Integer getOrderAdded() {
        return orderAdded;
    }

    public void setOrderAdded(Integer orderAdded) {
        this.orderAdded = orderAdded;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdb='" + imdb + '\'' +
                ", type='" + type + '\'' +
                ", url=" + posterUrl +
                '}';
    }
}
