package com.pbo.movieBot.nlp;

import com.pbo.movieBot.nlp.generic.Pattern;
import com.pbo.movieBot.nlp.generic.Reducer;
import com.pbo.movieBot.nlp.generic.Token;
import com.pbo.movieBot.nlp.token.IntegerToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.util.List;

// TODO: Reimplement
public class IntegerTokenReducer implements Reducer<Integer> {
    private Pattern pattern = createPattern();

    public IntegerTokenReducer() {
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public Token<Integer> reduce(List<Token<?>> tokens) {
        StringToken token = (StringToken) tokens.get(0);
        String strValue = token.getValue();
        int intValue = Integer.parseInt(strValue);
        return new IntegerToken(intValue, token.getStringPart());
    }

    @Override
    public List<Token<?>> reduceList(List<Token<?>> tokens) {
        return null;
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
