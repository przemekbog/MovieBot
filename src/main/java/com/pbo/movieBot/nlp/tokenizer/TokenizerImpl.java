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
        String collectedNum = "";
        for(int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            if(Character.isDigit(c)) {
                collectedNum += c;
                continue;
            }

            if(Character.isLetter(c)) {
                collectedString += c;
                continue;
            }

            if(!collectedString.equals("")) {
                tokens.add(new StringToken(collectedString, collectedString + " "));
            }

            if(!collectedNum.equals("")) {
                String stringPart = collectedNum;

                if(c.equals(' ')) {
                    stringPart += ' ';
                }

                tokens.add(new IntegerToken(Integer.parseInt(collectedNum), stringPart));
            }

            if(!c.equals(' ')) {
                tokens.add(new CharacterToken(c, c.toString()));
            }

            collectedString = "";
            collectedNum = "";
        }

        if(!collectedString.equals("")) {
            tokens.add(new StringToken(collectedString, collectedString));
        }

        if(!collectedNum.equals("")) {
            tokens.add(new IntegerToken(Integer.parseInt(collectedNum), collectedNum));
        }

        return tokens;
    }
}
