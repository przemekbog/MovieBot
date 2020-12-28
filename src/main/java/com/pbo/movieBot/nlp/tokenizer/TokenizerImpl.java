package com.pbo.movieBot.nlp.tokenizer;

import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.base.Tokenizer;
import com.pbo.movieBot.nlp.token.CharacterToken;
import com.pbo.movieBot.nlp.token.IntegerToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.util.ArrayList;
import java.util.List;

public class TokenizerImpl implements Tokenizer {
    @Override
    public List<Token<?>> tokenize(String s) {
        ArrayList<Token<?>> tokens = new ArrayList<>();

        String collectedString = "";
        for(int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            if(Character.isDigit(c)) {
                String number = "";

                while(Character.isDigit(c)) {
                    number += c;
                    i++;
                    c = s.charAt(i);
                }

                tokens.add(new IntegerToken(Integer.parseInt(number), number));
            }

            if(Character.isLetter(c)) {
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
