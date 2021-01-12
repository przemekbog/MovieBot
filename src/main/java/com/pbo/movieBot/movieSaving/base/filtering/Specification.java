package com.pbo.movieBot.movieSaving.base.filtering;

public interface Specification<T> {
    boolean isSatisfied(T obj);
}
