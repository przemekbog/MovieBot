package com.pbo.movieBot.movieReservations.base.filtering;

import com.pbo.movieBot.movieReservations.base.MovieReservation;

public class TitleSpecification implements Specification<MovieReservation> {
    private String title;

    public TitleSpecification(String title) {
        this.title = title;
    }

    @Override
    public boolean isSatisfied(MovieReservation obj) {
        String otherTitle = obj.getTitle();
        return otherTitle.equals(title);
    }
}
