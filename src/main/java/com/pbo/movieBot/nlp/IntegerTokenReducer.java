package com.pbo.movieBot.nlp;

import com.pbo.movieBot.nlp.generic.Pattern;
import com.pbo.movieBot.nlp.generic.Reducer;
import com.pbo.movieBot.nlp.generic.Token;

import java.util.List;

public class IntegerTokenReducer implements Reducer<Integer> {
    private Pattern pattern = createPattern();

    public IntegerTokenReducer() {
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public Token<Integer> reduce(List<Token<?>> list) {
        StringToken token = (StringToken) list.get(0);
        String strValue = token.getValue();
        int intValue = Integer.parseInt(strValue);
        return new IntegerToken(intValue);
    }

    private Pattern createPattern() {
        return new Pattern() {
            @Override
            public int getTokenCount() {
                return 1;
            }

            @Override
            public boolean matches(List<Token<?>> tokens) {
                if(tokens.size() != 1) {
                    return false;
                }

                Token t = tokens.get(0);
                if(!(t instanceof StringToken)) {
                    return false;
                }

                StringToken token = (StringToken) t;

                return isInteger(token.getValue());
            }
        };
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

}
