package com.pbo.movieBot.bot.context.announcement;

import com.pbo.movieBot.bot.Bot;
import com.pbo.movieBot.bot.options.Configuration;
import com.pbo.movieBot.movieApi.MovieFetcher;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class ReservationReminder extends TimedEvent<MovieReservation> {

    private static final Color MESSAGE_COLOR = new Color(0x13AD8E);

    public ReservationReminder(MovieReservation reservation) {
        super(reservation);
    }

    @Override
    protected void run(MovieReservation reservation) {
        MessageChannel channel = getAnnouncementChannel();
        channel.sendMessage(getReminderEmbed(reservation)).queue();
    }

    private MessageEmbed getReminderEmbed(MovieReservation reservation) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setColor(MESSAGE_COLOR)
                .setDescription(getReminderText())
                .addField("Title", reservation.getTitle(), true)
                .addField("Date", reservation.getReservationDate().toString(), true)
                .addField("Time", reservation.getReservationTime().toString(), true);

        return builder.build();
    }

    private MessageEmbed getPosterEmbed(String title) {
        Movie movie = getMovieInfo(title);

        String displayTitle = String.format("%s (%s)", movie.getTitle(), movie.getYear());
        String posterUrl = movie.getPosterUrl();

        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle(displayTitle)
                .setImage(posterUrl)
                .setColor(MESSAGE_COLOR);

        return builder.build();
    }

    private boolean hasPoster(String movieTitle) {
        Movie movie = getMovieInfo(movieTitle);
        return !movie.getPosterUrl().equals("N/A");
    }

    private Movie getMovieInfo(String title) {
        MovieFetcher fetcher = MovieFetcher.withMovieTitle(title);
        return fetcher.fetch();
    }

    private MessageChannel getAnnouncementChannel() {
        long id = Configuration.getAnnouncementChannelId();
        return Bot.getJdaInstance().getTextChannelById(id);
    }

    private String getUpNextString() {
        return """
                ```
                                                       \s
                 _____ _____    _____ _____ __ __ _____\s
                |  |  |  _  |  |   | |   __|  |  |_   _|
                |  |  |   __|  | | | |   __|-   -| | | \s
                |_____|__|     |_|___|_____|__|__| |_| \s
                                                                               \s
                ```
                """;
    }

    private String getReminderText() {
        return """
                ```
                                                                \s
                 _____ _____ _____ _____ _____ ____  _____ _____\s
                | __  |   __|     |     |   | |    \\|   __| __  |
                |    -|   __| | | |-   -| | | |  |  |   __|    -|
                |__|__|_____|_|_|_|_____|_|___|____/|_____|__|__|
                                                                               \s
                ```
                """;
    }
}
