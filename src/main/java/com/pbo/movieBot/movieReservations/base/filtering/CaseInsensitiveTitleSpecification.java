package com.pbo.movieBot.movieReservations.base.filtering;

import com.pbo.movieBot.movieReservations.base.MovieReservation;

public class CaseInsensitiveTitleSpecification implements Specification<MovieReservation> {

    private String uppercaseTitle;

    public CaseInsensitiveTitleSpecification(String title) {
        this.uppercaseTitle = title.toUpperCase();
    }

    @Override
    public boolean isSatisfied(MovieReservation obj) {
        String objUppercaseTitle = obj.getTitle().toUpperCase();
        return uppercaseTitle.equals(objUppercaseTitle);
    }
}
