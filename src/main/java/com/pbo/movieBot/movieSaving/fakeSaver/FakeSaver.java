package com.pbo.movieBot.movieSaving.fakeSaver;

import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieSaving.base.MovieEntry;
import com.pbo.movieBot.movieSaving.base.MovieSaver;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FakeSaver implements MovieSaver {

    private List<MovieEntry> movieList = new ArrayList<>();

    public FakeSaver() {
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
    public Iterator<MovieEntry> iterator() {
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
    public boolean add(MovieEntry movieEntry) {
        return movieList.add(movieEntry);
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
    public boolean addAll(@NotNull Collection<? extends MovieEntry> c) {
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
