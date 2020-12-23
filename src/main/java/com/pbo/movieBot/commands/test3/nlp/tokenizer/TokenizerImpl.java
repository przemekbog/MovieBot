package com.pbo.movieBot.commands.test3.nlp.tokenizer;

import com.pbo.movieBot.commands.test3.nlp.token.CharacterToken;
import com.pbo.movieBot.commands.test3.nlp.token.StringToken;
import com.pbo.movieBot.nlp.generic.Token;
import com.pbo.movieBot.nlp.generic.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenizerImpl implements Tokenizer {
    @Override
    public List<Token<?>> tokenize(String s) {
        ArrayList<Token<?>> tokens = new ArrayList<>();
        String[] words = s.split(" ");

        for(String word : words) {
            tokens.addAll(tokenizeWord(word));
        }

        return tokens;
    }

    private List<Token<?>> tokenizeWord(String word) {
        ArrayList<Token<?>> tokens = new ArrayList<>();
        String collected = "";

        for(char c : word.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                collected += c;
            } else {
                if(!collected.equals("")) {
                    tokens.add(new StringToken(collected));
                }
                tokens.add(new CharacterToken(c));
                collected = "";
            }
        }

        if(!collected.equals("")) {
            tokens.add(new StringToken(collected));
        }

        return tokens;
    }

}
