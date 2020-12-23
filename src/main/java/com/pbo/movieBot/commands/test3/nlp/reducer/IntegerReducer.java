package com.pbo.movieBot.commands.test3.nlp.reducer;

import com.pbo.movieBot.commands.test3.nlp.token.IntegerToken;
import com.pbo.movieBot.commands.test3.nlp.token.StringToken;
import com.pbo.movieBot.nlp.generic.Pattern;
import com.pbo.movieBot.nlp.generic.Reducer;
import com.pbo.movieBot.nlp.generic.Token;

import java.util.List;

public class IntegerReducer implements Reducer<Integer> {

    @Override
    public Pattern getPattern() {
        return new Pattern() {
            @Override
            public int getTokenCount() {
                return 1;
            }

            @Override
            public boolean matches(List<Token<?>> tokens) {
                Token t = tokens.get(0);
                if(!(t instanceof StringToken)) {
                    return false;
                }

                StringToken token = (StringToken) t;
                String value = token.getValue();
                if(!isNumber(value)) {
                    return false;
                } else {
                    return true;
                }
            }
        };
    }

    @Override
    public Token<Integer> reduce(List<Token<?>> tokens) {
        String value = (String) tokens.get(0).getValue();
        return new IntegerToken(Integer.parseInt(value));
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
