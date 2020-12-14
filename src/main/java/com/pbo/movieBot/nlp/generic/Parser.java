package com.pbo.movieBot.nlp.generic;

import java.util.List;

public interface Parser<T> {
    T parse(List<Token<?>> tokens);
}
