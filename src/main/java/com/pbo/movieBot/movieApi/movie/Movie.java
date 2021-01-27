package com.pbo.movieBot.movieApi.movie;


public class Movie {

    /* Note:
     * Variable names start from a big letter because gson
     * is case sensitive and the names in source data
     * start from a capital letter.
     */

    private String Title = "N/A";
    private String Year = "N/A";
    private String Rated = "N/A";
    private String Released = "N/A";
    private String Runtime = "N/A";
    private String Genre = "N/A";
    private String Director = "N/A";
    private String Writer = "N/A";
    private String Actors = "N/A";
    private String Plot = "N/A";
    private String Poster = "N/A";

    private String Language = "N/A";
    private String Country = "N/A";
    private String Awards = "N/A";
    private String Metascore = "N/A";
    private String imdbRating = "N/A";
    private String imdbVotes = "N/A";
    private String imdbID = "N/A";
    private String Type = "N/A";
    private String DVD = "N/A";
    private String BoxOffice = "N/A";
    private String Production = "N/A";
    private String Website = "N/A";
    private String Response = "N/A";

    private MovieRating[] Ratings = new MovieRating[] {};

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleaseDate() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getPosterUrl() {
        return Poster;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public MovieType getType() {
        return MovieType.byName(Type);
    }

    public String getDVD() {
        return DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public String getWebsite() {
        return Website;
    }

    public String getResponse() {
        return Response;
    }

    public MovieRating[] getRatings() {
        return Ratings;
    }
}

