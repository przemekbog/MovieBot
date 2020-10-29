package com.pbo.movieBot.key;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

// Bad way to do this, but oh well... what can I do?
public class APIKeys {

    private static KeyHolder instance;

    static {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader("options/keys.json"));
            instance = gson.fromJson(reader, KeyHolder.class);
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

    private static class KeyHolder {
        private String movieAPIKey = "undefined";
        private String discordAPIKey = "undefined";

        public String getMovieAPIKey() {
            return movieAPIKey;
        }

        public String getDiscordAPIKey() {
            return discordAPIKey;
        }
    }
}
