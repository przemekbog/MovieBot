package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.time.LocalDate;
import java.util.List;

public class TomorrowReducer implements Reducer<LocalDate> {
    @Override
    public Pattern getPattern() {
        return new SingleTokenPattern(new StringToken("tomorrow", ""));
    }

    @Override
    public Token<LocalDate> reduce(List<Token<?>> tokens) {
        String stringPart = tokens.get(0).getStringPart();
        return new DateToken(getTomorrowDate(), stringPart);
    }

    private LocalDate getTomorrowDate() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int dayOfYear = today.getDayOfYear();
        int daysInYear = today.lengthOfYear();

        if(dayOfYear >= daysInYear) {
            return LocalDate.ofYearDay(year + 1, 1);
        } else {
            return LocalDate.ofYearDay(year, dayOfYear + 1);
        }
    }
}
