package com.pbo.movieBot.nlp.base;

import java.util.List;

public interface Parser<T> {
    T parse(List<Token<?>> tokens);
}
