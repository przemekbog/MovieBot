package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.token.DateTimeToken;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.TimeToken;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TimeDateReducer implements Reducer<LocalDateTime> {
    @Override
    public Pattern getPattern() {
        return new ListPattern(
                new ClassPattern(TimeToken.class),
                new ClassPattern(DateToken.class)
        );
    }

    @Override
    public Token<LocalDateTime> reduce(List<Token<?>> tokens) {
        LocalTime time = (LocalTime) tokens.get(0).getValue();
        LocalDate date = (LocalDate) tokens.get(1).getValue();
        String stringPart = tokens.get(0).getStringPart() + tokens.get(1).getStringPart();

        return new DateTimeToken(date, time, stringPart);
    }
}
