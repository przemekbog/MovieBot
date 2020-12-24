package com.pbo.movieBot.nlp.base;

import java.util.List;

public interface Reducer<T> {
    Pattern getPattern();
    Token<T> reduce(List<Token<?>> tokens);
    List<Token<?>> reduceList(List<Token<?>> tokens);
}
