package com.pbo.movieBot.nlp.pattern;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Token;

import java.util.List;

public class ClassPattern implements Pattern {

    private Class<?> clazz;

    public ClassPattern(Class<?> clazz) {
        this.clazz = clazz;
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

        Token<?> token = tokens.get(0);
        return token.getClass().equals(clazz);
    }
}
