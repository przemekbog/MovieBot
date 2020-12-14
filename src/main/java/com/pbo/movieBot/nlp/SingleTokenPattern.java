package com.pbo.movieBot.nlp;

import com.pbo.movieBot.nlp.generic.Pattern;
import com.pbo.movieBot.nlp.generic.Token;

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
