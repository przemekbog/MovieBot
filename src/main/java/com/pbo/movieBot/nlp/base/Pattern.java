package com.pbo.movieBot.nlp.base;

import java.util.List;

public interface Pattern {
    int getTokenCount();
    boolean matches(List<Token<?>> tokens);
}
