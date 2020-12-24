package com.pbo.movieBot.commands.test3.nlp.token;

import com.pbo.movieBot.nlp.generic.Token;

import java.sql.Time;
import java.time.LocalTime;

public class TimeToken extends Token<LocalTime> {

    public TimeToken(LocalTime value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "TimeToken{" +
                "value=" + value +
                '}';
    }
}
