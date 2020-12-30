package com.pbo.movieBot.movieSaving.fakeSaver;

import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieSaving.base.MovieEntry;
import com.pbo.movieBot.movieSaving.base.MovieSaver;

import java.util.*;

public class FakeSaver implements MovieSaver {

    private Map<String, MovieEntry> movies = new HashMap<>();

    public FakeSaver() {
    }

    @Override
    public Collection<MovieEntry> getMovies() {
        return movies.values();
    }

    @Override
    public Optional<MovieEntry> getMovie(String name) {
        if(!movies.containsKey(name)) {
            return Optional.empty();
        }

        return Optional.of(movies.get(name));
    }

    @Override
    public void addMovie(MovieEntry movie) {
        movies.put(movie.getTitle(), movie);
    }

    @Override
    public void removeMovie(MovieEntry movie) {
        movies.remove(movie.getTitle(), movie);
    }
}
