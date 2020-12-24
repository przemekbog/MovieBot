package com.pbo.movieBot.nlp.token;

import com.pbo.movieBot.nlp.base.Token;

public class StringToken extends Token<String> {

    public StringToken(String value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "value=" + value +
                '}';
    }
}
