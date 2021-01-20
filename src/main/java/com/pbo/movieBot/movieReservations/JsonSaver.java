package com.pbo.movieBot.movieReservations;

import com.google.gson.Gson;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.ReservationSaver;
import com.pbo.movieBot.movieReservations.base.filtering.Specification;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class JsonSaver implements ReservationSaver {

    private Set<MovieReservation> movieReservationSet = new HashSet<>();
    private String filePath;

    public JsonSaver(String filePath) {
        this.filePath = filePath;

        movieReservationSet.addAll(loadMovies());
    }

    @Override
    public List<MovieReservation> getBySpecification(Specification<MovieReservation> specification) {
        ArrayList<MovieReservation> collected = new ArrayList<>();

        for(MovieReservation movie : movieReservationSet) {
            if(specification.isSatisfied(movie)) {
                collected.add(movie);
            }
        }

        return collected;
    }

    @Override
    public int size() {
        return movieReservationSet.size();
    }

    @Override
    public boolean isEmpty() {
        return movieReservationSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return movieReservationSet.contains(o);
    }

    @NotNull
    @Override
    public Iterator<MovieReservation> iterator() {
        return movieReservationSet.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return movieReservationSet.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return movieReservationSet.toArray(a);
    }

    @Override
    public boolean add(MovieReservation movieReservation) {
        boolean result = movieReservationSet.add(movieReservation);
        saveMovies();
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = movieReservationSet.remove(o);
        saveMovies();
        return result;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return movieReservationSet.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends MovieReservation> c) {
        boolean result = movieReservationSet.addAll(c);
        saveMovies();
        return result;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        boolean result = movieReservationSet.removeAll(c);
        saveMovies();
        return result;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        boolean result = movieReservationSet.retainAll(c);
        saveMovies();
        return result;
    }

    @Override
    public void clear() {
        movieReservationSet.clear();
        saveMovies();
    }

    private List<MovieReservation> loadMovies() {
        return getMoviesFromFile(filePath);
    }

    private void saveMovies() {
        MovieReservation[] movieEntries = getArrayFromCollection(movieReservationSet);
        saveMoviesToFile(movieEntries, filePath);
    }

    private List<MovieReservation> getMoviesFromFile(String filePath) {
        try {
            FileReader reader = new FileReader(getMoviesFile());

            Gson gson = new Gson();
            MovieReservation[] movies = gson.fromJson(reader, MovieReservation[].class);
            if(movies == null) {
                movies = new MovieReservation[0];
            }

            return List.of(movies);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private void saveMoviesToFile(MovieReservation[] movies, String filePath) {
        try {
            FileWriter writer = new FileWriter(getMoviesFile());

            Gson gson = new Gson();
            gson.toJson(movies, writer);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getMoviesFile() {
        try {
            File file = new File(filePath);

            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            return file;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not find nor create the file with movie reservations");
        }
    }

    private MovieReservation[] getArrayFromCollection(Collection<MovieReservation> movieEntries) {
        MovieReservation[] array = new MovieReservation[movieEntries.size()];
        movieEntries.toArray(array);
        return array;
    }


}
