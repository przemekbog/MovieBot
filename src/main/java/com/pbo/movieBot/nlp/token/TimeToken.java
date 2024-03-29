package com.pbo.movieBot.nlp.token;

import com.pbo.movieBot.nlp.base.Token;

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
