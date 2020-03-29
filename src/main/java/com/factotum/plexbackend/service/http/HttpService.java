package com.factotum.plexbackend.service.http;

import java.net.URL;
import java.util.Map;
import java.util.Set;

public interface HttpService {

    <T> T performGetRequest(URL path, Class<T> expectedResult);

    <T> T performGetRequest(URL path, Map<String, String> parameters, Class<T> expectedResult);

    <T> Set<T> performGetRequestWithArrayReturn(URL path, Map<String, String> parameters, Class<T> expectedResult);

}

