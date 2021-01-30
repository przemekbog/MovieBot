package com.pbo.movieBot.bot.context.announcement;

import java.time.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public abstract class TimedEvent<TArgs> {
    private Timer timer = new Timer();
    private TArgs args;

    public TimedEvent(TArgs args) {
        this.args = args;
    }

    protected abstract void run(TArgs args);

    public void cancel() {
        timer.cancel();
    }

    public void schedule(LocalDate date, LocalTime time) {
        schedule(LocalDateTime.of(
                date,
                time
        ));
    }

    public void schedule(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        if(dateTime.isBefore(now)) {
            return;
        }

        Date date = getDateFromLocalDateTime(dateTime);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                TimedEvent.this.run(args);
            }
        };

        timer.schedule(task, date);
    }

    private Date getDateFromLocalDateTime(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}
