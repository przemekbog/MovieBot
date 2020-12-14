package com.pbo.movieBot.nlp.generic;

import java.util.List;

public interface Tokenizer {
    List<Token<?>> tokenize(String s);
}
