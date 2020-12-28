package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.StringToken;
import com.pbo.movieBot.nlp.token.TimeToken;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class AtTimeReducer implements Reducer<LocalTime> {
    @Override
    public Pattern getPattern() {
        return new ListPattern(
                new SingleTokenPattern(new StringToken("at", "")),
                new ClassPattern(TimeToken.class)
        );
    }

    @Override
    public Token<LocalTime> reduce(List<Token<?>> tokens) {
        LocalTime time = (LocalTime) tokens.get(1).getValue();
        String stringPart = tokens.get(0).getStringPart() + tokens.get(1).getStringPart();
        return new TimeToken(time, stringPart);
    }
}
