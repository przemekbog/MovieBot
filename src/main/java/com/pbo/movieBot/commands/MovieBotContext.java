package com.pbo.movieBot.commands;

import com.pbo.movieBot.movieReservations.JsonSaver;
import com.pbo.movieBot.movieReservations.base.ReservationSaver;

import java.time.*;
import java.util.*;

public class MovieBotContext {
    private ReservationSaver saver;

    public MovieBotContext(String path) {
        saver = new JsonSaver(path);
    }

    public ReservationSaver getSaver() {
        return saver;
    }

    private Date getDateFromLocalDateTime(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}
