package com.pbo.movieBot.movieApi;

import java.net.*;
import java.util.HashMap;
import java.util.Map;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

public class ParameterisedUrlBuilder {

    private URL baseUrl;
    private Map<String, String> parameters = new HashMap<>();

    public ParameterisedUrlBuilder(String url) {
        try {
            this.baseUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Url given was invalid: " + url);
        }
    }

    public URL build() {
        StringBuilder builder = new StringBuilder();

        int i = 0;
        for(String parameter : parameters.keySet()) {
            String value = parameters.get(parameter);

            builder.append((i == 0) ? "?" : "&");
            builder.append(parameter + "=" + value);
            i++;
        }

        try {
            return new URL(baseUrl.toString() + builder.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getCause());
        }

    }

    public ParameterisedUrlBuilder setParameter(String name, String value) {
        String encodedName = encodeString(name);
        String encodedValue = encodeString(value);

        parameters.put(encodedName, encodedValue);
        return this;
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }

    private String encodeString(String src) {
        try {
            return URLEncoder.encode(src, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getCause());
        }
    }

}
