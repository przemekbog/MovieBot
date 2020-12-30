package com.pbo.movieBot.movieSaving.jsonSaver;

import com.google.gson.Gson;
import com.pbo.movieBot.movieSaving.base.MovieEntry;
import com.pbo.movieBot.movieSaving.base.MovieSaver;

import java.io.*;
import java.util.*;

public class JsonSaver implements MovieSaver {

    private Map<String, MovieEntry> movieEntryMap = new HashMap<>();
    private String filePath;

    public JsonSaver(String filePath) {
        this.filePath = filePath;

        addAllMovies(loadMovies());
    }

    @Override
    public Collection<MovieEntry> getMovies() {
        return movieEntryMap.values();
    }

    @Override
    public Optional<MovieEntry> getMovie(String name) {
        if(!movieEntryMap.containsKey(name)) {
            return Optional.empty();
        }

        return Optional.of(movieEntryMap.get(name));
    }

    @Override
    public void addMovie(MovieEntry movie) {
        movieEntryMap.put(movie.getTitle(), movie);
        saveMovies();
    }

    @Override
    public void removeMovie(MovieEntry movie) {
        movieEntryMap.remove(movie.getTitle(), movie);
        saveMovies();
    }

    private void addAllMovies(List<MovieEntry> movies) {
        for(MovieEntry movie : movies) {
            movieEntryMap.put(movie.getTitle(), movie);
        }
    }

    private List<MovieEntry> loadMovies() {
        return getMoviesFromFile(filePath);
    }

    private void saveMovies() {
        MovieEntry[] movieEntries = getArrayFromList(new ArrayList<>(movieEntryMap.values()));
        saveMoviesToFile(movieEntries, filePath);
    }

    private List<MovieEntry> getMoviesFromFile(String filePath) {
        try {
            FileReader reader = new FileReader(getMoviesFile());
            Gson gson = new Gson();
            MovieEntry[] movies = gson.fromJson(reader, MovieEntry[].class);
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

    private MovieEntry[] getArrayFromList(List<MovieEntry> movieEntries) {
        MovieEntry[] array = new MovieEntry[movieEntries.size()];
        movieEntries.toArray(array);
        return array;
    }
}
