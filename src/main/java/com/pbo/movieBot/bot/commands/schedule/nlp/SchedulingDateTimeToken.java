package com.pbo.movieBot.bot.commands.schedule.nlp;

import com.pbo.movieBot.nlp.base.Token;

import java.time.LocalDateTime;

public class SchedulingDateTimeToken extends Token<LocalDateTime> {
    public SchedulingDateTimeToken(LocalDateTime value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "SchedulingDateTimeToken{" +
                "value=" + value +
                ", stringPart='" + stringPart + '\'' +
                '}';
    }
}
