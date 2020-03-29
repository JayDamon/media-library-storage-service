package com.factotum.plexbackend.service.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HttpServiceImpl implements HttpService {

    private static final Logger log = LoggerFactory.getLogger(HttpServiceImpl.class);

    @Override
    public <T> T performGetRequest(URL path, Class<T> expectedResult) {
        return this.performGetRequest(path, null, expectedResult);
    }

    @Override
    public <T> T performGetRequest(URL url, Map<String, String> parameters, Class<T> expectedResult) {

        StringBuilder content = null;

        try {

            content = getHttpContent(url, parameters, content);

            if (content != null) {
                return new ObjectMapper().readValue(content.toString(), expectedResult);
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {

            String message = "Unable to map resulting json to Object";

            if (content != null) {
                message = message + " <" + content.toString() + ">";
            }

            log.error(message, e);
        } catch (UnsupportedEncodingException e) {
            log.error("Unable to add provided parameters", e);
        } catch (IOException e) {
            log.error("Unable to process get request", e);
        }

        return null;
    }

    @Override
    public <T> Set<T> performGetRequestWithArrayReturn(URL path, Map<String, String> parameters, Class<T> expectedResult) {

        StringBuilder content = null;

        try {
            content = getHttpContent(path, parameters, content);

            if (content != null) {
                return new ObjectMapper().readValue(content.toString(), new TypeReference<>() {
                });
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {

            String message = "Unable to map resulting json to Object";

            if (content != null) {
                message = message + " <" + content.toString() + ">";
            }

            log.error(message, e);
        } catch (UnsupportedEncodingException e) {
            log.error("Unable to add provided parameters", e);
        } catch (IOException e) {
            log.error("Unable to process get request", e);
        }

        return null;
    }

    private StringBuilder getHttpContent(URL url, Map<String, String> parameters, StringBuilder content) throws IOException {

        if (parameters != null && !parameters.isEmpty()) {

            StringJoiner updateUrl = new StringJoiner("&", url.toString() + "?", "");

            for (Map.Entry<String, String> parameter : parameters.entrySet()) {
                updateUrl.add(parameter.getKey() + "=" + parameter.getValue());
            }

            url = new URL(updateUrl.toString());
        }

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        content = performHttpRequest(con);
        return content;
    }

    private StringBuilder performHttpRequest(HttpURLConnection con) {

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            return content;
        } catch (IOException e) {
            log.error("Unable to retrieve data from URL <" + con.getURL().toString() + ">", e);
            return null;
        }
    }

}

