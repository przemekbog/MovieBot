package com.pbo.movieBot.bot.options;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    private static final String PATH = "options/config.json";
    private static ConfigurationHolder instance;

    static {
        instance = loadConfig();
    }

    public static String getMovieAPIKey() {
        return instance.getMovieAPIKey();
    }

    public static String getDiscordAPIKey() {
        return instance.getDiscordAPIKey();
    }

    public static String getStatusMessage() {
        return instance.getStatusMessage();
    }

    public static long getDefaultChannelId() {
        return instance.getDefaultChannelId();
    }

    public static void setDefaultChannelId(long defaultChannelId) {
        instance.setDefaultChannelId(defaultChannelId);
    }

    public static void save() {
        try {
            FileWriter writer = new FileWriter(getFile(PATH));

            Gson gson = new Gson();
            gson.toJson(instance, writer);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ConfigurationHolder loadConfig() {
        try {
            FileReader reader = new FileReader(getFile(PATH));

            Gson gson = new Gson();
            ConfigurationHolder holder = gson.fromJson(reader, ConfigurationHolder.class);
            return holder;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not load the configuration file");
        }
    }

    private static File getFile(String filePath) {
        try {
            File file = new File(filePath);

            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            return file;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not find nor create file with path: " + filePath);
        }
    }
}
