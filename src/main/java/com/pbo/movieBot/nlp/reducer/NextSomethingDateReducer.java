package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.*;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.time.LocalDate;
import java.util.List;

public class NextSomethingDateReducer implements Reducer<LocalDate> {
    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new SingleTokenPattern(new StringToken("next", "")),
                        new OrPattern(
                                new SingleTokenPattern(new StringToken("week", "")),
                                new SingleTokenPattern(new StringToken("month", ""))
                        )
                )
        );
    }

    @Override
    public Token<LocalDate> reduce(List<Token<?>> tokens) {
        String chosenOption = (String) tokens.get(1).getValue();

        LocalDate chosenDate = switch (chosenOption) {
            case "week" -> getNextWeek();
            case "month" -> getNextMonth();
            default -> throw new IllegalStateException("Unexpected value: " + chosenOption);
        };

        String stringPart = tokens.get(0).getStringPart() + tokens.get(1).getStringPart();

        return new DateToken(chosenDate, stringPart);
    }

    private LocalDate getNextWeek() {
        LocalDate today = LocalDate.now();

        int day = today.getDayOfYear() + 7;
        int year = today.getYear();
        int daysInYear = today.lengthOfYear();

        if(day > daysInYear) {
            day = day % daysInYear;
            year++;
        }

        return LocalDate.ofYearDay(year, day);
    }

    private LocalDate getNextMonth() {
        LocalDate today = LocalDate.now();

        int year = today.getYear();
        int month = today.getMonth().getValue() + 1;
        int day = today.getDayOfMonth();

        if(month > 12) {
            month = 1;
            year++;
        }

        LocalDate firstDayNextMonth = LocalDate.of(year, month, 1);
        int daysInNextMonth = firstDayNextMonth.lengthOfMonth();
        if(day > daysInNextMonth) {
            day = daysInNextMonth;
        }

        return LocalDate.of(year, month, day);
    }
}
