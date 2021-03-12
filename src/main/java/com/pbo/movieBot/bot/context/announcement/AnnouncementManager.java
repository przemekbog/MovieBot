package com.pbo.movieBot.bot.context.announcement;

import com.pbo.movieBot.bot.Bot;
import com.pbo.movieBot.bot.context.MovieBotContext;
import com.pbo.movieBot.bot.options.Configuration;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class AnnouncementManager {
    private HashMap<MovieReservation, Collection<TimedEvent>> announcements = new HashMap<>();
    private MovieBotContext context;

    public AnnouncementManager(MovieBotContext context) {
        this.context = context;
    }

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
        TimedEvent<MovieReservation> reminder = new ReservationReminder(reservation);
        TimedEvent<MovieReservation> finalAnnouncement = new TimedMovieAnnouncer(reservation, context);

        LocalDateTime reservationDateTime = reservation.getReservationDateTime();

        finalAnnouncement.schedule(reservationDateTime.minusMinutes(5));
        reminder.schedule(reservationDateTime.minusMinutes(60));

        return Set.of(finalAnnouncement, reminder);
    }
}
