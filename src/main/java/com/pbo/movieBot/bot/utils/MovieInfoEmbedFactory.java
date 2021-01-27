package com.pbo.movieBot.bot.utils;

import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieApi.movie.MovieRating;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class MovieInfoEmbedFactory {

    private static final Color DEFAULT_COLOR = new Color(0xd49324);;

    public MessageEmbed getInfo(Movie movie) {
        return getInfo(movie, DEFAULT_COLOR);
    }

    public MessageEmbed getInfo(Movie movie, Color color) {
         EmbedBuilder builder = new EmbedBuilder();

         builder.setColor(color);
         setTitle(builder, movie);
         appendPlot(builder, movie);
         appendInfo(builder, movie);
         appendRatings(builder, movie);
         setPoster(builder, movie);
         setFooter(builder);

         return builder.build();
    }

    public MessageEmbed getShortInfo(Movie movie) {
        return getShortInfo(movie, DEFAULT_COLOR);
    }

    public MessageEmbed getShortInfo(Movie movie, Color color) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setColor(color);
        setTitle(builder, movie);
        appendPlot(builder, movie);
        setPoster(builder, movie);
        setFooter(builder);

        return builder.build();
    }

    private void setTitle(EmbedBuilder builder, Movie movie) {
        String title = movie.getTitle();
        String year = movie.getYear();
        String titleString = String.format("%s (%s)", title, year);

        String url = movie.getWebsite();
        if(url.equals("N/A")) {
            builder.setTitle(titleString);
        } else {
            builder.setTitle(titleString, url);
        }
    }

    private void appendPlot(EmbedBuilder builder, Movie movie) {
        builder.addField("__**PLOT:**__", movie.getPlot(), false);
    }

    private void appendInfo(EmbedBuilder builder, Movie movie) {
        builder.addField("", "__**INFO:**__", false);

        builder .addField("runtime", movie.getRuntime(), true)
                .addField("released", movie.getReleaseDate(), true)
                .addField("director", movie.getDirector(), true);
    }

    private void appendRatings(EmbedBuilder builder, Movie movie) {
        builder.addField("", "__**RATINGS:**__", false);

        MovieRating[] ratings = movie.getRatings();
        for(MovieRating rating : ratings) {
            builder.addField(rating.getSource(),rating.getValue(), true);
        }
    }

    private void setPoster(EmbedBuilder builder, Movie movie) {
        if(!movie.getPosterUrl().equals("N/A")) {
            builder.setImage(movie.getPosterUrl());
        }
    }

    private void setFooter(EmbedBuilder builder) {
        builder.setFooter("Movie Bot [oo]<");
    }
}
