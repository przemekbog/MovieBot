package com.pbo.movieBot.movieSaving.base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class MovieEntry {
    private String title;
    private String reservationDate;
    private String reservationTime;

    public MovieEntry(String title, LocalDate reservationDate, LocalTime reservationTime) {
        this.title = title;
        this.reservationDate = reservationDate.toString();
        this.reservationTime = reservationTime.toString();
    }

    public MovieEntry(String title, LocalDateTime dateTime) {
        this.title = title;
        this.reservationDate = dateTime.toLocalDate().toString();
        this.reservationTime = dateTime.toLocalTime().toString();
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReservationDate() {
        return LocalDate.parse(reservationDate);
    }

    public LocalTime getReservationTime() {
        return LocalTime.parse(reservationTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntry that = (MovieEntry) o;
        return Objects.equals(title, that.title)
                && Objects.equals(reservationDate, that.reservationDate)
                && Objects.equals(reservationTime, that.reservationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, reservationDate, reservationTime);
    }

    @Override
    public String toString() {
        return "MovieEntry{" +
                "title='" + title + '\'' +
                ", reservationDate='" + reservationDate + '\'' +
                ", reservationTime='" + reservationTime + '\'' +
                '}';
    }
}
