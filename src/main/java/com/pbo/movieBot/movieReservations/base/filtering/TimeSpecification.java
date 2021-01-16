package com.pbo.movieBot.movieReservations.base.filtering;

import com.pbo.movieBot.movieReservations.base.MovieReservation;

import java.time.LocalTime;

public class TimeSpecification implements Specification<MovieReservation> {
    private LocalTime time;

    public TimeSpecification(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean isSatisfied(MovieReservation obj) {
        LocalTime otherTime = obj.getReservationTime();
        return otherTime.equals(time);
    }
}
