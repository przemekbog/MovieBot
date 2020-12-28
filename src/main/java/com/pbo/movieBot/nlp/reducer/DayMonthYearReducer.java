package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.CharacterToken;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.IntegerToken;

import java.time.LocalDate;
import java.util.List;

public class DayMonthYearReducer implements Reducer<LocalDate> {
    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new ClassPattern(IntegerToken.class),
                        new SingleTokenPattern(new CharacterToken('.', "")),
                        new ClassPattern(IntegerToken.class),
                        new SingleTokenPattern(new CharacterToken('.', "")),
                        new ClassPattern(IntegerToken.class)
                ),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 5;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        int day = (Integer) tokens.get(0).getValue();
                        int month = (Integer) tokens.get(2).getValue();
                        int year = (Integer) tokens.get(4).getValue();

                        try {
                            LocalDate.of(year, month, day);
                            return true;
                        } catch (Exception e) {
                            return false;
                        }
                    }
                }
        );
    }

    @Override
    public Token<LocalDate> reduce(List<Token<?>> tokens) {
        int day = (Integer) tokens.get(0).getValue();
        int month = (Integer) tokens.get(2).getValue();
        int year = (Integer) tokens.get(4).getValue();

        LocalDate date = LocalDate.of(year, month, day);

        return new DateToken(date, getStringPart(tokens));
    }

    private String getStringPart(List<Token<?>> tokens) {
        StringBuilder builder = new StringBuilder();

        for(Token<?> t : tokens) {
            builder.append(t.getStringPart());
        }

        return builder.toString();
    }
}
