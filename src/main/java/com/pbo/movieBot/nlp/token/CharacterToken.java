package com.pbo.movieBot.nlp.token;

import com.pbo.movieBot.nlp.base.Token;

public class CharacterToken extends Token<Character>  {

    public CharacterToken(Character value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "CharacterToken{" +
                "value=" + value +
                '}';
    }
}
