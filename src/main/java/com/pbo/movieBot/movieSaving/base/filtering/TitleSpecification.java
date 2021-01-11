package com.pbo.movieBot.movieSaving.base.filtering;

import com.pbo.movieBot.movieSaving.base.MovieEntry;

public class TitleSpecification implements Specification<MovieEntry> {
    private String title;

    public TitleSpecification(String title) {
        this.title = title;
    }

    @Override
    public boolean isSatisfied(MovieEntry obj) {
        String otherTitle = obj.getTitle();
        return otherTitle.equals(title);
    }
}
