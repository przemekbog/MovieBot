package com.pbo.movieBot.nlp.generic;

import java.util.List;

public interface Pattern {
    int getTokenCount();
    boolean matches(List<Token<?>> tokens);
}
