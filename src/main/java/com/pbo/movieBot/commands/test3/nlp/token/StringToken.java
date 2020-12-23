package com.pbo.movieBot.commands.test3.nlp.token;

import com.pbo.movieBot.nlp.generic.Token;

public class StringToken extends Token<String> {
    public StringToken(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "value=" + value +
                '}';
    }
}
