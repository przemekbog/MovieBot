package com.pbo.movieBot.commands.test3.nlp.reducer;

import com.pbo.movieBot.nlp.token.CharacterToken;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.token.IntegerToken;

import java.time.LocalTime;
import java.util.List;

// TODO: Reimplement
public class HourMinuteTimeReducer implements Reducer<LocalTime> {
    @Override
    public Pattern getPattern() {
        return new Pattern() {
            @Override
            public int getTokenCount() {
                return 3;
            }

            @Override
            public boolean matches(List<Token<?>> tokens) {
                if(tokens.size() != 3) {
                    return false;
                }

                if(!(tokens.get(0) instanceof IntegerToken)
                    || !(tokens.get(1) instanceof CharacterToken)
                    || !(tokens.get(2) instanceof IntegerToken)) {
                    return false;
                }

                Character character = (Character) tokens.get(1).getValue();
                if(!character.equals(':')) {
                    return false;
                }

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
        };
    }

    @Override
    public Token<LocalTime> reduce(List<Token<?>> tokens) {
        throw new IllegalStateException("Not implemented");
//        Integer hours = (Integer) tokens.get(0).getValue();
//        Integer minutes = (Integer) tokens.get(2).getValue();
//
//        LocalTime time = LocalTime.of(hours, minutes);
//        return new TimeToken(time);
    }
}
