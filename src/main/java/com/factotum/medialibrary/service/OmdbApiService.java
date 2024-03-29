package com.factotum.medialibrary.service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.factotum.medialibrary.dto.SearchDto;
import com.factotum.medialibrary.dto.TitleDto;
import com.factotum.medialibrary.service.http.HttpService;

@Service
public class OmdbApiService implements TitleApiService {

    private static final String API_KEY_PARAM = "apikey";
    private static final String SEARCH = "s";

    private final HttpService httpService;
    private final String apiKey;
    private final URL apiUrl;

    public OmdbApiService(
            HttpService httpService,
            @Value("${omdbapi.api.key}") String apiKey,
            @Value("${omdbapi.api.url}") URL apiUrl) {
        this.httpService = httpService;
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    @Override
    public TitleDto[] searchByTitle(String title) {

        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title must not be null or empty");
        }

        title = title.replaceAll(" ", "_");

        Map<String, String> parameters = new HashMap<>();
        parameters.put(API_KEY_PARAM, this.apiKey);
        parameters.put(SEARCH, title);

        SearchDto search = this.httpService.performGetRequest(this.apiUrl, parameters, SearchDto.class);

        if (search != null && search.responseReturned()) {
            return search.getSearch();
        } else {
            return new TitleDto[]{};
        }

    }
}
