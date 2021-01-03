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

public class DayMonthWordYearReducer implements Reducer<LocalDate> {

    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new ClassPattern(IntegerToken.class),
                        new ClassPattern(MonthToken.class),
                        new ClassPattern(IntegerToken.class)
                ),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 3;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        Integer day = (Integer) tokens.get(0).getValue();
                        Month month = (Month) tokens.get(1).getValue();
                        Integer year = (Integer) tokens.get(2).getValue();

                        if(year < 0) {
                            return false;
                        }

                        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
                        int daysInMonth = firstDayOfMonth.lengthOfMonth();
                        if(day < 1 || day > daysInMonth) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
        );
    }

    @Override
    public Token<LocalDate> reduce(List<Token<?>> tokens) {
        IntegerToken dayToken = (IntegerToken) tokens.get(0);
        MonthToken monthToken = (MonthToken) tokens.get(1);
        IntegerToken yearToken = (IntegerToken) tokens.get(2);

        LocalDate date = LocalDate.of(yearToken.getValue(), monthToken.getValue(), dayToken.getValue());
        String stringPart = dayToken.getStringPart() + monthToken.getStringPart() + yearToken.getStringPart();

        return new DateToken(date, stringPart);
    }
}
