package com.pbo.movieBot.movieSaving.base;

import java.util.Collection;
import java.util.Optional;

public interface MovieSaver {
    Collection<MovieEntry> getMovies();
    Optional<MovieEntry> getMovie(String name);
    void addMovie(MovieEntry movie);
    void removeMovie(MovieEntry movie);
}
