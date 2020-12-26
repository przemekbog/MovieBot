package com.pbo.movieBot.commands.test3.nlp.tokenizer;

import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.base.Tokenizer;
import com.pbo.movieBot.nlp.token.CharacterToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.util.ArrayList;
import java.util.List;

public class TokenizerImpl implements Tokenizer {
    @Override
    public List<Token<?>> tokenize(String s) {
        ArrayList<Token<?>> tokens = new ArrayList<>();

        String collectedString = "";
        for(Character c : s.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                collectedString += c;
                continue;
            }

            if(!collectedString.equals("")) {
                tokens.add(new StringToken(collectedString, collectedString + " "));
            }

            if(!c.equals(' ')) {
                tokens.add(new CharacterToken(c, c.toString()));
            }

            collectedString = "";
        }

        if(!collectedString.equals("")) {
            tokens.add(new StringToken(collectedString, collectedString));
        }

        return tokens;
    }
}
