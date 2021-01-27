package com.pbo.movieBot.bot.context.announcement;

import com.pbo.movieBot.bot.Bot;
import com.pbo.movieBot.bot.options.Configuration;
import com.pbo.movieBot.movieReservations.base.MovieReservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AnnouncementManager {
    private HashMap<MovieReservation, Collection<TimedEvent>> announcements = new HashMap<>();

    public void schedule(Collection<MovieReservation> reservations) {
        for(MovieReservation reservation : reservations) {
            schedule(reservation);
        }
    }

    public void schedule(MovieReservation reservation) {
        announcements.put(reservation, getAnnouncementsForReservation(reservation));
    }

    public void remove(MovieReservation reservation) {
        if(!announcements.containsKey(reservation)) {
            return;
        }

        announcements.get(reservation).forEach((timedEvent -> timedEvent.cancel()));
        announcements.remove(reservation);
    }

    public void clear() {
        announcements.clear();
    }

    private Collection<TimedEvent> getAnnouncementsForReservation(MovieReservation reservation) {
        TimedEvent<MovieReservation> announcement1 = new TimedMovieAnnouncer(reservation);
        LocalDate date = reservation.getReservationDate();
        LocalTime time = reservation.getReservationTime();

        announcement1.schedule(date, time);

        Set<TimedEvent> set = new HashSet<>();
        set.add(announcement1);

        return set;
    }
}
