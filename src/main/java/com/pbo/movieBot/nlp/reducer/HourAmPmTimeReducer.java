package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.token.TimeToken;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.token.IntegerToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.time.LocalTime;
import java.util.List;

// TODO: Reimplement
public class HourAmPmTimeReducer implements Reducer<LocalTime> {
    @Override
    public Pattern getPattern() {
        return new Pattern() {
            @Override
            public int getTokenCount() {
                return 2;
            }

            @Override
            public boolean matches(List<Token<?>> tokens) {
                if(!(tokens.get(0) instanceof IntegerToken)
                        || !(tokens.get(1) instanceof StringToken)) {
                    return false;
                }

                Integer hour = ((IntegerToken) tokens.get(0)).getValue();
                if(hour < 0 || hour > 12) {
                    return false;
                }

                String dayPart = ((StringToken) tokens.get(1)).getValue();
                if(!dayPart.equals("am") && !dayPart.equals("pm")) {
                    return false;
                }

                return true;
            }
        };
    }

    @Override
    public Token<LocalTime> reduce(List<Token<?>> list) {

        Integer hour = (Integer) list.get(0).getValue();
        String dayPart = (String) list.get(1).getValue();

        if(hour == 12) {
            hour = 0;
        }

        if(dayPart.equals("pm")) {
            hour += 12;
        }

        LocalTime time = LocalTime.of(hour, 00);
        String stringPart = list.get(0).getStringPart() + list.get(1).getStringPart();
        return new TimeToken(time, stringPart);
    }
}
