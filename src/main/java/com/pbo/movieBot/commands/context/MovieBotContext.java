package com.pbo.movieBot.commands.context;

import com.jagrosh.jdautilities.commons.JDAUtilitiesInfo;
import com.pbo.movieBot.Bot;
import com.pbo.movieBot.movieReservations.JsonSaver;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.ReservationSaver;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.internal.JDAImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.util.*;

public class MovieBotContext {
    private ReservationSaver saver;
    private HashMap<MovieReservation, Collection<TimedEvent>> announcements;
    private String defaultChannel = "754324314366541896";

    public MovieBotContext(String path) {
        saver = new JsonSaver(path);
        TimedLambda<MovieReservation> lambda = new TimedLambda<>(null, this::announceMovie);
        LocalDateTime now = LocalDateTime.now();
        lambda.schedule(now.plusSeconds(5));
    }

    public ReservationSaver getSaver() {
        return saver;
    }

    private void scheduleAnnouncements(Collection<MovieReservation> reservations) {
        announcements.clear();

        for(MovieReservation reservation : reservations) {
            announcements.put(reservation, getAnnouncementsForReservation(reservation));
        }
    }

    private Collection<TimedEvent> getAnnouncementsForReservation(MovieReservation reservation) {
        TimedLambda<MovieReservation> announcement1 = new TimedLambda<>(reservation, this::announceMovie);
        LocalDateTime reservationDateTime =
                LocalDateTime.of(
                        reservation.getReservationDate(),
                        reservation.getReservationTime()
                );
        announcement1.schedule(reservationDateTime);

        Set<TimedEvent> set = new HashSet<>();
        set.add(announcement1);

        return set;
    }

    private void announceMovie(MovieReservation reservation) {
        Bot.getJdaInstance().getTextChannelById(defaultChannel).sendMessage("Test").queue();
    }

}
