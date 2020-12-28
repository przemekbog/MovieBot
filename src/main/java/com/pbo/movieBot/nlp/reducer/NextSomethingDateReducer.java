package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class NextSomethingDateReducer implements Reducer<LocalDate> {
    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new SingleTokenPattern(new StringToken("next", "")),
                        new ClassPattern(StringToken.class)
                ),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 2;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        List<String> correctOptions = List.of("week", "month");
                        String chosenOption = (String) tokens.get(1).getValue();
                        return correctOptions.contains(chosenOption);
                    }
                }
        );
    }

    @Override
    public Token<LocalDate> reduce(List<Token<?>> tokens) {
        String chosenOption = (String) tokens.get(1).getValue();

        LocalDate chosenDate;

        // TODO: Change to the new switch expression.
        switch (chosenOption) {
            case "week":
                chosenDate = getNextWeek();
                break;
            case "month":
                chosenDate = getNextMonth();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + chosenOption);
        };

        String stringPart = tokens.get(0).getStringPart() + tokens.get(1).getStringPart();

        return new DateToken(chosenDate, stringPart);
    }

    private LocalDate getNextWeek() {
        LocalDate today = LocalDate.now();

        int day = today.getDayOfYear() + 7;
        int year = today.getYear();

        if(day >= 365) {
            year++;
            day = day % 365;
        }

        return LocalDate.ofYearDay(year, day);
    }

    private LocalDate getNextMonth() {
        LocalDate today = LocalDate.now();

        int year = today.getYear();
        int month = today.getMonth().getValue() + 1;
        int day = today.getDayOfMonth();

        if(month >= 13) {
            month = 1;
            year++;
        }

        return LocalDate.of(year, month, day);
    }
}
