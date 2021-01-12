package com.pbo.movieBot.movieSaving.base.filtering;

import com.pbo.movieBot.movieSaving.base.MovieEntry;

import java.time.LocalDate;

public class DateSpecification implements Specification<MovieEntry> {
    private LocalDate date;

    public DateSpecification(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isSatisfied(MovieEntry obj) {
        LocalDate otherDate = obj.getReservationDate();
        return otherDate.equals(date);
    }
}
