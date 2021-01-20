package com.pbo.movieBot.bot.context;

import com.pbo.movieBot.bot.Bot;
import com.pbo.movieBot.movieReservations.base.MovieReservation;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AnnouncementManager {
    private HashMap<MovieReservation, Collection<TimedEvent>> announcements = new HashMap<>();
    private String defaultChannel = "754324314366541896";

    public void schedule(Collection<MovieReservation> reservations) {
        for(MovieReservation reservation : reservations) {
            schedule(reservation);
        }
    }

    public void schedule(MovieReservation reservation) {
        announcements.put(reservation, getAnnouncementsForReservation(reservation));
    }

    public void clear() {
        announcements.clear();
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
        Bot.getJdaInstance().getTextChannelById(defaultChannel).sendMessage(reservation.getTitle()).queue();
        announcements.remove(reservation);
    }
}
