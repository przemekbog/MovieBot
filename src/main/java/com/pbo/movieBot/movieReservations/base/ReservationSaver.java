package com.pbo.movieBot.movieReservations.base;

import com.pbo.movieBot.movieReservations.base.filtering.Specification;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface ReservationSaver extends Collection<MovieReservation> {
    List<MovieReservation> getBySpecification(Specification<MovieReservation> specification);
}
