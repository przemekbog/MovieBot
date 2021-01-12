package com.pbo.movieBot.movieSaving;

import com.google.gson.Gson;
import com.pbo.movieBot.movieSaving.base.MovieEntry;
import com.pbo.movieBot.movieSaving.base.MovieSaver;
import com.pbo.movieBot.movieSaving.base.filtering.Specification;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class JsonSaver implements MovieSaver {

    private Set<MovieEntry> movieEntrySet = new HashSet<>();
    private String filePath;

    public JsonSaver(String filePath) {
        this.filePath = filePath;

        movieEntrySet.addAll(loadMovies());
    }

    @Override
    public List<MovieEntry> getBySpecification(Specification<MovieEntry> specification) {
        ArrayList<MovieEntry> collected = new ArrayList<>();

        for(MovieEntry movie : movieEntrySet) {
            if(specification.isSatisfied(movie)) {
                collected.add(movie);
            }
        }

        return collected;
    }

    @Override
    public int size() {
        return movieEntrySet.size();
    }

    @Override
    public boolean isEmpty() {
        return movieEntrySet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return movieEntrySet.contains(o);
    }

    @NotNull
    @Override
    public Iterator<MovieEntry> iterator() {
        return movieEntrySet.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return movieEntrySet.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return movieEntrySet.toArray(a);
    }

    @Override
    public boolean add(MovieEntry movieEntry) {
        boolean result = movieEntrySet.add(movieEntry);
        saveMovies();
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = movieEntrySet.remove(o);
        saveMovies();
        return result;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return movieEntrySet.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends MovieEntry> c) {
        boolean result = movieEntrySet.addAll(c);
        saveMovies();
        return result;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        boolean result = movieEntrySet.removeAll(c);
        saveMovies();
        return result;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        boolean result = movieEntrySet.retainAll(c);
        saveMovies();
        return result;
    }

    @Override
    public void clear() {
        movieEntrySet.clear();
        saveMovies();
    }

    private List<MovieEntry> loadMovies() {
        return getMoviesFromFile(filePath);
    }

    private void saveMovies() {
        MovieEntry[] movieEntries = getArrayFromCollection(movieEntrySet);
        saveMoviesToFile(movieEntries, filePath);
    }

    private List<MovieEntry> getMoviesFromFile(String filePath) {
        try {
            FileReader reader = new FileReader(getMoviesFile());

            Gson gson = new Gson();
            MovieEntry[] movies = gson.fromJson(reader, MovieEntry[].class);
            if(movies == null) {
                movies = new MovieEntry[0];
            }

            return List.of(movies);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private void saveMoviesToFile(MovieEntry[] movies, String filePath) {
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

    private MovieEntry[] getArrayFromCollection(Collection<MovieEntry> movieEntries) {
        MovieEntry[] array = new MovieEntry[movieEntries.size()];
        movieEntries.toArray(array);
        return array;
    }


}
