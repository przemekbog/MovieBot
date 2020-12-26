package com.pbo.movieBot.commands.test3.nlp.reducer;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.token.IntegerToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.util.List;

public class IntegerReducer implements Reducer<Integer> {

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
                        return isNumber(value);
                    }
                }
        );
    }

    @Override
    public Token<Integer> reduce(List<Token<?>> tokens) {
        String value = (String) tokens.get(0).getValue();
        return new IntegerToken(Integer.parseInt(value), tokens.get(0).getStringPart());
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
