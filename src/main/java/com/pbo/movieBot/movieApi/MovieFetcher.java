package com.pbo.movieBot.movieApi;

import com.google.gson.Gson;
import com.pbo.movieBot.key.APIKeys;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieApi.movie.MovieType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MovieFetcher {
    public static final String API_URL;
    public static final String API_KEY;

    private ParameterisedUrlBuilder urlBuilder = new ParameterisedUrlBuilder(API_URL);

    static {
        API_URL = "http://www.omdbapi.com/";
        API_KEY = APIKeys.getMovieAPIKey();
    }

    private MovieFetcher() {
        urlBuilder.setParameter("apikey", API_KEY);
    }

    public static MovieFetcher withMovieTitle(String title) {
        MovieFetcher fetcher = new MovieFetcher();
        fetcher.setTitle(title);
        return fetcher;
    }

    public static MovieFetcher withMovieId(String id) {
        MovieFetcher fetcher = new MovieFetcher();
        fetcher.setId(id);
        return fetcher;
    }

    public Movie fetch() {
        String json = getUrlContents(urlBuilder.build());
        Movie movie = new Gson().fromJson(json, Movie.class);
        return movie;
    }

    public MovieFetcher setTitle(String title) {
        urlBuilder.setParameter("t", title);
        return this;
    }

    public String getTitle() {
        return urlBuilder.getParameter("t");
    }

    public MovieFetcher setId(String id) {
        urlBuilder.setParameter("i", id);
        return this;
    }

    public String getId() {
        return urlBuilder.getParameter("i");
    }

    public MovieFetcher setType(MovieType type) {
        urlBuilder.setParameter("type", type.getName());
        return this;
    }

    public MovieType getType() {
        return MovieType.byName(urlBuilder.getParameter("type"));
    }

    public MovieFetcher setYearOfRelease(String year) {
        urlBuilder.setParameter("y", year);
        return this;
    }

    public String getYearOfRelease() {
        return urlBuilder.getParameter("y");
    }

    public MovieFetcher setPlot(String plot) {
        urlBuilder.setParameter("plot", plot);
        return this;
    }

    public String getPlot() {
        return urlBuilder.getParameter("plot");
    }

    public MovieFetcher setCallback(String callback) {
        urlBuilder.setParameter("callback", callback);
        return this;
    }

    public String getCallback() {
        return urlBuilder.getParameter("callback");
    }

//    This feature will be added in the future by the api's creators,
//    but right now it does not work
//
//    public MovieFetcher setApiVersion(String version) {
//        urlBuilder.setParameter("v", version);
//        return this;
//    }
//
//    public String getApiVersion() {
//        return urlBuilder.getParameter("v");
//    }

    private static String getUrlContents(URL url) {
        StringBuilder content = new StringBuilder();

        try {
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }

            bufferedReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
