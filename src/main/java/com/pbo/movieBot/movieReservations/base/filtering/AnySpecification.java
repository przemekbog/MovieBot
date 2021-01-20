package com.pbo.movieBot.movieReservations.base.filtering;

public class AnySpecification<T> implements Specification<T> {
    @Override
    public boolean isSatisfied(T obj) {
        return true;
    }
}
