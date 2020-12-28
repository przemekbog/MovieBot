package com.pbo.movieBot.nlp.token;

import com.pbo.movieBot.nlp.base.Token;

import java.time.LocalDate;

public class DateToken extends Token<LocalDate> {
    public DateToken(LocalDate value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "DateToken{" +
                "value=" + value +
                '}';
    }
}
