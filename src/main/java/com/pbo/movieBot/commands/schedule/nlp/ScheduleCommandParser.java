package com.pbo.movieBot.commands.schedule.nlp;

import com.pbo.movieBot.movieSaving.base.MovieReservation;
import com.pbo.movieBot.nlp.base.Parser;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.token.DateTimeToken;

import java.util.List;

public class ScheduleCommandParser implements Parser<MovieReservation> {
    @Override
    public MovieReservation parse(List<Token<?>> tokens) {
        if(!areValid(tokens)) {
            throw new IllegalArgumentException("The last token must be a DateTimeToken");
        }

        int lastIndex = tokens.size() - 1;
        List<Token<?>> titlePart = tokens.subList(0, lastIndex);
        String title = combineStringParts(titlePart);

        DateTimeToken timeToken = (DateTimeToken) tokens.get(lastIndex);
        return new MovieReservation(title, timeToken.getValue());
    }

    String combineStringParts(List<Token<?>> tokens) {
        StringBuilder builder = new StringBuilder();

        for(Token<?> token : tokens) {
            builder.append(token.getStringPart());
        }

        return builder.toString();
    }

    boolean areValid(List<Token<?>> tokens) {
        int lastIndex = tokens.size() - 1;
        Token<?> lastToken = tokens.get(lastIndex);

        return lastToken instanceof DateTimeToken;
    }
}
