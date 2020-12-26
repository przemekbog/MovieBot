package com.pbo.movieBot.nlp.base;

import java.util.List;

public interface ListReducer {
    List<Token<?>> reduce(List<Token<?>> tokens);
}
