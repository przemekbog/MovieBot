package com.pbo.movieBot.commands.test3.nlp.token;

import com.pbo.movieBot.nlp.generic.Token;

public class CharacterToken extends Token<Character>  {
    public CharacterToken(Character value) {
        super(value);
    }

    @Override
    public String toString() {
        return "CharacterToken{" +
                "value=" + value +
                '}';
    }
}
