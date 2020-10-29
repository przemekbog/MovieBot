package com.pbo.movieBot.movieApi.movie;

import java.util.HashMap;
import java.util.Map;

public enum MovieType {
    MOVIE("movie"),
    SERIES("series"),
    EPISODE("episode");

    private static Map<String, MovieType> nameToMovieTypeMap = new HashMap<>();

    private String type;

    static {
        for(MovieType movieType : MovieType.values()) {
            nameToMovieTypeMap.put(movieType.getName(), movieType);
        }
    }

    MovieType(String type) {
        this.type = type;
    }

    public static MovieType byName(String name) {
        return nameToMovieTypeMap.get(name);
    }

    public String getName() {
        return type;
    }
}
