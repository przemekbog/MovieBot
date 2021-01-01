package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.token.MonthToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.time.Month;
import java.util.List;

public class MonthReducer implements Reducer<Month> {

    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ClassPattern(StringToken.class),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 1;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        String value = (String) tokens.get(0).getValue();
                        return getMonthFromString(value) != null;
                    }
                }
        );
    }

    @Override
    public Token<Month> reduce(List<Token<?>> tokens) {
        Token<?> token = tokens.get(0);
        String value = (String) token.getValue();
        return new MonthToken(getMonthFromString(value), token.getStringPart());
    }

    private Month getMonthFromString(String name) {
        return switch (name.toLowerCase()) {
            case "january", "jan" -> Month.JANUARY;
            case "february", "feb" -> Month.FEBRUARY;
            case "march", "mar" -> Month.MARCH;
            case "april", "apr" -> Month.APRIL;
            case "may" -> Month.MAY;
            case "june", "jun" -> Month.JUNE;
            case "july", "jul" -> Month.JULY;
            case "august", "aug" -> Month.AUGUST;
            case "september", "sep" -> Month.SEPTEMBER;
            case "october", "oct" -> Month.OCTOBER;
            case "november", "nov" -> Month.NOVEMBER;
            case "december", "dec" -> Month.DECEMBER;
            default -> null;
        };
    }
}
