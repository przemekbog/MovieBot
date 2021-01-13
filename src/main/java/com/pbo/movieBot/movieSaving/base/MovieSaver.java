package com.pbo.movieBot.movieSaving.base;

import com.pbo.movieBot.movieSaving.base.filtering.Specification;

import java.util.Collection;
import java.util.List;

public interface MovieSaver extends Collection<MovieReservation> {
    List<MovieReservation> getBySpecification(Specification<MovieReservation> specification);
}
