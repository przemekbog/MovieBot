package com.pbo.movieBot.bot.context;

import com.pbo.movieBot.bot.context.announcement.AnnouncementManager;
import com.pbo.movieBot.movieReservations.JsonSaver;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.ReservationSaver;
import com.pbo.movieBot.movieReservations.base.filtering.AnySpecification;
import com.pbo.movieBot.movieReservations.base.filtering.Specification;

import java.util.List;

public class MovieBotContext {
    private ReservationSaver saver;
    private AnnouncementManager announcementManager = new AnnouncementManager(this);

    public MovieBotContext(String path) {
        saver = new JsonSaver(path);
        announcementManager.schedule(saver);
    }

    public void addReservation(MovieReservation reservation) {
        saver.add(reservation);
        announcementManager.schedule(reservation);
    }

    public void removeReservation(MovieReservation reservation) {
        saver.remove(reservation);
        announcementManager.remove(reservation);
    }

    public int getNumberOfReservations() {
        return saver.size();
    }

    public List<MovieReservation> getMoviesBySpecification(Specification<MovieReservation> specification) {
        return saver.getBySpecification(specification);
    }
}
