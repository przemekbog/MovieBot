package com.pbo.movieBot.nlp.base;

import java.util.List;

public interface Tokenizer {
    List<Token<?>> tokenize(String s);
}
