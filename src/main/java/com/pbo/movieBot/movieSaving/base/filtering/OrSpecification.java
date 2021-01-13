package com.pbo.movieBot.movieSaving.base.filtering;

import java.util.List;

public class OrSpecification<T> implements Specification<T> {
    private List<Specification<T>> specifications;

    public OrSpecification(List<Specification<T>> specifications) {
        this.specifications = specifications;
    }

    public OrSpecification(Specification<T>... specifications) {
        this.specifications = List.of(specifications);
    }

    @Override
    public boolean isSatisfied(T obj) {
        for(Specification<T> specification : specifications) {
            if(specification.isSatisfied(obj)) {
                return true;
            }
        }

        return false;
    }
}
