package com.pbo.movieBot.movieSaving.base;

import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieSaving.base.filtering.MovieSpecification;
import com.pbo.movieBot.movieSaving.base.filtering.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MovieSaver extends Collection<MovieEntry> {
    List<MovieEntry> getBySpecification(Specification<MovieEntry> specification);
}
