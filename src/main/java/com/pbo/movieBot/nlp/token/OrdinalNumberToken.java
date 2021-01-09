package com.pbo.movieBot.nlp.token;

import com.pbo.movieBot.nlp.OrdinalNumber;
import com.pbo.movieBot.nlp.base.Token;

public class OrdinalNumberToken extends Token<OrdinalNumber> {
    public OrdinalNumberToken(OrdinalNumber value, String stringPart) {
        super(value, stringPart);
    }

    @Override
    public String toString() {
        return "OrdinalNumberToken{" +
                "value=" + value +
                '}';
    }
}
