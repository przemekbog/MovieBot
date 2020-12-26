package com.pbo.movieBot.nlp.pattern;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Token;

import java.util.List;

public class SingleTokenPattern implements Pattern {
    protected Token<?> token;

    public SingleTokenPattern(Token<?> token) {
        this.token = token;
    }

    @Override
    public int getTokenCount() {
        return 1;
    }

    @Override
    public boolean matches(List<Token<?>> tokens) {
        if(tokens.size() != 1) {
            return false;
        }

        return token.equals(tokens.get(0));
    }
}
