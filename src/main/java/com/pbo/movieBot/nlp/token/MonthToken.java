package com.pbo.movieBot.nlp.token;

import com.pbo.movieBot.nlp.base.Token;

import java.time.Month;

public class MonthToken extends Token<Month> {
    public MonthToken(Month value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "MonthToken{" +
                "value=" + value +
                '}';
    }
}
