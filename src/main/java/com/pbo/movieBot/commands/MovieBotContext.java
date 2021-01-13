package com.pbo.movieBot.commands;

import com.pbo.movieBot.movieSaving.JsonSaver;
import com.pbo.movieBot.movieSaving.base.MovieSaver;

public class MovieBotContext {
    private MovieSaver saver;

    public MovieBotContext(String path) {
        saver = new JsonSaver(path);
    }

    public MovieSaver getSaver() {
        return saver;
    }
}
