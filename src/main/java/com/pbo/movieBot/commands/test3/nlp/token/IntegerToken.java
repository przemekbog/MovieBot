package com.pbo.movieBot.commands.test3.nlp.token;

import com.pbo.movieBot.nlp.generic.Token;

public class IntegerToken extends Token<Integer> {

    public IntegerToken(Integer value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "IntegerToken{" +
                "value=" + value +
                '}';
    }
}
