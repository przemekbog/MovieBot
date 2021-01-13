package com.pbo.movieBot.movieSaving;

import com.pbo.movieBot.movieSaving.base.MovieReservation;
import com.pbo.movieBot.movieSaving.base.MovieSaver;
import com.pbo.movieBot.movieSaving.base.filtering.Specification;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FakeSaver implements MovieSaver {

    private List<MovieReservation> movieList = new ArrayList<>();

    public FakeSaver() {
    }

    @Override
    public List<MovieReservation> getBySpecification(Specification<MovieReservation> specification) {
        ArrayList<MovieReservation> collected = new ArrayList<>();

        for(MovieReservation movie : movieList) {
            if(specification.isSatisfied(movie)) {
                collected.add(movie);
            }
        }

        return collected;
    }

    @Override
    public int size() {
        return movieList.size();
    }

    @Override
    public boolean isEmpty() {
        return movieList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return movieList.contains(o);
    }

    @NotNull
    @Override
    public Iterator<MovieReservation> iterator() {
        return movieList.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return movieList.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return movieList.toArray(a);
    }

    @Override
    public boolean add(MovieReservation movieReservation) {
        return movieList.add(movieReservation);
    }

    @Override
    public boolean remove(Object o) {
        return movieList.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return movieList.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends MovieReservation> c) {
        return movieList.addAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return movieList.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return movieList.retainAll(c);
    }

    @Override
    public void clear() {
        movieList.clear();
    }


}
