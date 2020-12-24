package com.pbo.movieBot.commands.test3.nlp.tokenizer;

import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.base.Tokenizer;

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
        // TODO: Reimplement
        throw new IllegalStateException("Not implemented");
//
//        ArrayList<Token<?>> tokens = new ArrayList<>();
//        String collected = "";
//
//        for(char c : word.toCharArray()) {
//            if(Character.isLetterOrDigit(c)) {
//                collected += c;
//            } else {
//                if(!collected.equals("")) {
//                    tokens.add(new StringToken(collected));
//                }
//                tokens.add(new CharacterToken(c, String.valueOf(c)));
//                collected = "";
//            }
//        }
//
//        if(!collected.equals("")) {
//            tokens.add(new StringToken(collected));
//        }
//
//        return tokens;
    }

}
