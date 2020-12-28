package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.time.LocalDate;
import java.util.List;

public class OnDateReducer implements Reducer<LocalDate> {
    @Override
    public Pattern getPattern() {
        return new ListPattern(
                new SingleTokenPattern(new StringToken("on", "")),
                new ClassPattern(DateToken.class)
        );
    }

    @Override
    public Token<LocalDate> reduce(List<Token<?>> tokens) {
        LocalDate date = (LocalDate) tokens.get(1).getValue();
        String stringPart = tokens.get(0).getStringPart() + tokens.get(1).getStringPart();
        return new DateToken(date, stringPart);
    }
}
