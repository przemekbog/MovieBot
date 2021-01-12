package com.pbo.movieBot.movieSaving.base.filtering;

import com.pbo.movieBot.movieSaving.base.MovieEntry;

import java.time.LocalTime;

public class TimeSpecification implements Specification<MovieEntry> {
    private LocalTime time;

    public TimeSpecification(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean isSatisfied(MovieEntry obj) {
        LocalTime otherTime = obj.getReservationTime();
        return otherTime.equals(time);
    }
}
