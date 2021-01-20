package com.pbo.movieBot.movieReservations.base.filtering;

public interface Specification<T> {
    boolean isSatisfied(T obj);
}
