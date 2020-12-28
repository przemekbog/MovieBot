package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.token.TimeToken;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.CharacterToken;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.token.IntegerToken;

import java.time.LocalTime;
import java.util.List;

public class HourMinuteTimeReducer implements Reducer<LocalTime> {
    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new ClassPattern(IntegerToken.class),
                        new SingleTokenPattern(new CharacterToken(':', "")),
                        new ClassPattern(IntegerToken.class)
                ),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 3;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        Integer hours  = (Integer) tokens.get(0).getValue();
                        if(hours < 0 || hours > 23) {
                            return false;
                        }

                        Integer minutes = (Integer) tokens.get(2).getValue();
                        if(minutes < 0 || minutes > 59) {
                            return false;
                        }

                        return true;
                    }
                }
        );
    }

    @Override
    public Token<LocalTime> reduce(List<Token<?>> tokens) {
        Integer hours = (Integer) tokens.get(0).getValue();
        Integer minutes = (Integer) tokens.get(2).getValue();
        LocalTime time = LocalTime.of(hours, minutes);

        String stringPart = tokens.get(0).getStringPart()
                        + tokens.get(1).getStringPart()
                        + tokens.get(2).getStringPart();

        return new TimeToken(time, stringPart);
    }
}
