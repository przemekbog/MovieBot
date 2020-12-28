package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.*;
import com.pbo.movieBot.nlp.token.StringToken;
import com.pbo.movieBot.nlp.token.TimeToken;

import java.time.LocalTime;
import java.util.List;

public class TimeAmPmReducer implements Reducer<LocalTime> {
    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new ClassPattern(TimeToken.class),
                        new ClassPattern(StringToken.class)
                ),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 2;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        LocalTime time = (LocalTime) tokens.get(0).getValue();
                        String amPmPart = (String) tokens.get(1).getValue();

                        if(time.getHour() > 12) {
                            return false;
                        }

                        if(!amPmPart.equals("am") && !amPmPart.equals("pm")) {
                            return false;
                        }

                        return true;
                    }
                }
        );
    }

    @Override
    public Token<LocalTime> reduce(List<Token<?>> tokens) {
        TimeToken timeToken = (TimeToken) tokens.get(0);
        StringToken stringToken = (StringToken) tokens.get(1);

        LocalTime oldTime = timeToken.getValue();
        LocalTime newTime;
        if(stringToken.getValue().equals("pm")) {
            int newHour = oldTime.getHour() + 12;
            int newMinute = oldTime.getMinute();

            if(newHour == 24) {
                newHour = 0;
            }

            newTime = LocalTime.of(newHour, newMinute);
        } else {
            newTime = oldTime;
        }

        String stringPart = timeToken.getStringPart() + stringToken.getStringPart();

        return new TimeToken(newTime, stringPart);
    }
}
