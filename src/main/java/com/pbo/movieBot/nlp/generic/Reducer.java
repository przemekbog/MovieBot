package com.pbo.movieBot.nlp.generic;

import java.util.List;

public interface Reducer<T> {
    Pattern getPattern();
    Token<T> reduce(List<Token<?>> tokens);
    List<Token<?>> reduceList(List<Token<?>> tokens);
}
