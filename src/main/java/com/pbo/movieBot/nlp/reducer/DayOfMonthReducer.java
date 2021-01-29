package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.token.OrdinalNumber;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class DayOfMonthReducer implements Reducer<LocalDate> {
    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new SingleTokenPattern(new StringToken("the", "")),
                        new ClassPattern(OrdinalNumberToken.class),
                        new SingleTokenPattern(new StringToken("of", "")),
                        new ClassPattern(MonthToken.class)
                ),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 4;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        int currentYear = LocalDate.now().getYear();
                        Month month = (Month) tokens.get(3).getValue();
                        OrdinalNumber ordinalDay = (OrdinalNumber) tokens.get(1).getValue();
                        int day = ordinalDay.getValue();

                        LocalDate firstDayOfMonth = LocalDate.of(currentYear, month, 1);
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
        OrdinalNumber ordinalDay = (OrdinalNumber) tokens.get(1).getValue();
        int day = ordinalDay.getValue();
        Month month = (Month) tokens.get(3).getValue();
        int currentYear = LocalDate.now().getYear();

        LocalDate date = LocalDate.of(currentYear, month, day);
        return new DateToken(date, getStringPart(tokens));
    }

    private String getStringPart(List<Token<?>> tokens) {
        StringBuilder builder = new StringBuilder();

        for(Token<?> token : tokens) {
            builder.append(token.getStringPart());
        }

        return builder.toString();
    }
}
