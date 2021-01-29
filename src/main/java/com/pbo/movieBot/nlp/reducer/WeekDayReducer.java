package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.token.StringToken;
import com.pbo.movieBot.nlp.token.WeekDay;
import com.pbo.movieBot.nlp.token.WeekDayToken;

import java.util.List;

public class WeekDayReducer implements Reducer<WeekDay> {
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
                        return WeekDay.isWeekDay(value);
                    }
                }
        );
    }

    @Override
    public Token<WeekDay> reduce(List<Token<?>> tokens) {
        String strValue = (String) tokens.get(0).getValue();
        WeekDay day = WeekDay.fromString(strValue);

        return new WeekDayToken(day, tokens.get(0).getStringPart());
    }
}
