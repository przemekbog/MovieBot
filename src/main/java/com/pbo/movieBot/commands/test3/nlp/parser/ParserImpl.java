package com.pbo.movieBot.commands.test3.nlp.parser;

import com.pbo.movieBot.nlp.generic.Parser;
import com.pbo.movieBot.nlp.generic.Token;

import java.util.List;

public class ParserImpl implements Parser<List<Token<?>>> {
    @Override
    public List<Token<?>> parse(List<Token<?>> tokens) {
        return tokens;
    }
}
