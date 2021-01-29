package com.pbo.movieBot.nlp.token;

import com.pbo.movieBot.nlp.base.Token;

public class WeekDayToken extends Token<WeekDay> {

    public WeekDayToken(WeekDay value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "WeekDayToken{" +
                "value=" + value +
                '}';
    }
}
