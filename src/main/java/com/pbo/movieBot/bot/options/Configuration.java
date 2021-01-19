package com.pbo.movieBot.bot.options;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

// Bad way to do this, but oh well... what can I do?
public class Configuration {

    private static ConfigurationHolder instance;

    static {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader("options/config.json"));
            instance = gson.fromJson(reader, ConfigurationHolder.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getMovieAPIKey() {
        return instance.getMovieAPIKey();
    }

    public static String getDiscordAPIKey() {
        return instance.getDiscordAPIKey();
    }

    private static class ConfigurationHolder {
        private String movieAPIKey = "not set";
        private String discordAPIKey = "not set";

        public String getMovieAPIKey() {
            return movieAPIKey;
        }

        public String getDiscordAPIKey() {
            return discordAPIKey;
        }
    }
}
