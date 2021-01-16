package com.pbo.movieBot.movieReservations.base.filtering;

import java.util.List;

public class AndSpecification<T> implements Specification<T> {
    private List<Specification<T>> specifications;

    public AndSpecification(List<Specification<T>> specifications) {
        this.specifications = specifications;
    }

    public AndSpecification(Specification<T>... specifications) {
        this.specifications = List.of(specifications);
    }

    @Override
    public boolean isSatisfied(T obj) {
        for(Specification<T> specification : specifications) {
            if(!specification.isSatisfied(obj)) {
                return false;
            }
        }

        return true;
    }
}
