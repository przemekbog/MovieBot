package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.IntegerToken;
import com.pbo.movieBot.nlp.token.MonthToken;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class MonthDayReducer implements Reducer<LocalDate> {
    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new ClassPattern(MonthToken.class),
                        new ClassPattern(IntegerToken.class)
                ),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 2;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        Month month = ((Month) tokens.get(0).getValue());
                        int currentYear = LocalDate.now().getYear();
                        LocalDate firstDayOfMonth = LocalDate.of(currentYear, month, 1);
                        int monthLength = firstDayOfMonth.lengthOfMonth();

                        int day = (Integer) tokens.get(1).getValue();

                        return day <= monthLength;
                    }
                }
        );
    }

    @Override
    public Token<LocalDate> reduce(List<Token<?>> tokens) {
        int year = LocalDate.now().getYear();
        Month month = (Month) tokens.get(0).getValue();
        int day = (Integer) tokens.get(1).getValue();

        String stringPart = tokens.get(0).getStringPart() + tokens.get(1).getStringPart();
        LocalDate date = LocalDate.of(year, month, day);
        return new DateToken(date, stringPart);
    }
}
