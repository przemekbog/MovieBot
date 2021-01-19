package com.pbo.movieBot.bot.commands.test4.nlp.parser;

import com.pbo.movieBot.nlp.base.Parser;
import com.pbo.movieBot.nlp.base.Token;

import java.util.List;

public class ParserImpl implements Parser<List<Token<?>>> {
    @Override
    public List<Token<?>> parse(List<Token<?>> tokens) {
        return tokens;
    }
}
