package com.pbo.movieBot.movieReservations.base.filtering;

import com.pbo.movieBot.movieReservations.base.MovieReservation;

import java.time.LocalDate;

public class DateSpecification implements Specification<MovieReservation> {
    private LocalDate date;

    public DateSpecification(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isSatisfied(MovieReservation obj) {
        LocalDate otherDate = obj.getReservationDate();
        return otherDate.equals(date);
    }
}
