package com.pbo.movieBot.nlp.token;

import com.pbo.movieBot.nlp.base.Token;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeToken extends Token<LocalDateTime> {
    public DateTimeToken(LocalDateTime value, String stringPart) {
        super(value, stringPart);
    }

    public DateTimeToken(LocalDate date, LocalTime time, String stringPart) {
        super(LocalDateTime.of(date, time), stringPart);
    }
}
