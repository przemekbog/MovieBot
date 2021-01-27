package com.pbo.movieBot.bot.context.announcement;

import com.pbo.movieBot.bot.Bot;
import com.pbo.movieBot.bot.options.Configuration;
import com.pbo.movieBot.bot.utils.MovieInfoEmbedFactory;
import com.pbo.movieBot.movieApi.MovieFetcher;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class TimedMovieAnnouncer extends TimedEvent<MovieReservation> {
    private static final Color MESSAGE_COLOR = new Color(0x1387AD);
    private MessageChannel channel;

    public TimedMovieAnnouncer(MovieReservation reservation) {
        super(reservation);
        channel = getChannelById(Configuration.getDefaultChannelId());
    }

    @Override
    protected void run(MovieReservation reservation) {
        channel.sendMessage(getAnnouncementEmbed(reservation)).queue();

        MovieFetcher fetcher = MovieFetcher.withMovieTitle(reservation.getTitle());
        Movie movie = fetcher.fetch();
        MessageEmbed infoEmbed = new MovieInfoEmbedFactory().getShortInfo(movie, MESSAGE_COLOR);

        channel.sendMessage(infoEmbed).queue();
    }

    private MessageEmbed getAnnouncementEmbed(MovieReservation reservation) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setDescription(getAnnouncementString())
                .setColor(MESSAGE_COLOR)
                .addField("Title", reservation.getTitle(), true)
                .addField("Date", reservation.getReservationDate().toString(), true)
                .addField("Time", reservation.getReservationTime().toString(), true);

        return builder.build();
    }

    private String getAnnouncementString() {
        return """
                ```
                                               __\s
                 _____ _____ _____ _____ _____|  |
                |     |     |  |  |     |   __|  |
                | | | |  |  |  |  |-   -|   __|__|
                |_|_|_|_____|\\___/|_____|_____|__|
                                                                                  \s
                ```
                """;
    }

    private MessageChannel getChannelById(long id) {
        return Bot.getJdaInstance().getTextChannelById(id);
    }

}
