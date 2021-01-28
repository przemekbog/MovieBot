package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.WeekDay;
import com.pbo.movieBot.nlp.token.WeekDayToken;

import java.time.LocalDate;
import java.util.List;

public class WeekDayToDateReducer implements Reducer<LocalDate> {

    @Override
    public Pattern getPattern() {
        return new ClassPattern(WeekDayToken.class);
    }

    @Override
    public Token<LocalDate> reduce(List<Token<?>> tokens) {
        WeekDay value = (WeekDay) tokens.get(0).getValue();
        String stringPart = tokens.get(0).getStringPart();
        LocalDate date = weekDayToDate(value);

        return new DateToken(date, stringPart);
    }

    private LocalDate weekDayToDate(WeekDay day) {
        LocalDate today = LocalDate.now();
        int currentDayIndex =  today.getDayOfWeek().ordinal();
        int dayIndex = day.ordinal();

        int offset = dayIndex - currentDayIndex;
        LocalDate date = today.plusDays(offset);

        if(currentDayIndex > dayIndex) {
            date = date.plusDays(7);
        }

        return date;
    }

}
