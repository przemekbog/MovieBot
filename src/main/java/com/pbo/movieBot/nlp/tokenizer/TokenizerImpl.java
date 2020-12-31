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

        String[] parts = s.split(" ");

        for(int i = 0; i < parts.length; i++) {
            String part = parts[i];
            List<Token<?>> partTokens = tokenizePart(part);
            Token<?> last = partTokens.get(partTokens.size() - 1);

            if(i < parts.length - 1) {
                last = addSpaceToStringPart(last);
            }

            partTokens.set(partTokens.size() - 1, last);
            tokens.addAll(partTokens);
        }

        return tokens;
    }

    private List<Token<?>> tokenizePart(String part) {
        ArrayList<Token<?>> tokens = new ArrayList<>();

        int i = 0;
        while(i < part.length()) {
            Character c = part.charAt(i);

            if(Character.isDigit(c)) {
                String number = "";

                while(i < part.length()) {
                    c = part.charAt(i);

                    if(!Character.isDigit(c)) {
                        break;
                    }

                    number += c;
                    i++;
                }

                tokens.add(new IntegerToken(Integer.parseInt(number), number));
                continue;
            }

            if(Character.isLetter(c)) {
                String text = "";

                while(i < part.length()) {
                    c = part.charAt(i);

                    if(!Character.isLetter(c)) {
                        break;
                    }

                    text += c;
                    i++;
                }

                tokens.add(new StringToken(text, text));
                continue;
            }

            tokens.add(new CharacterToken(c, c.toString()));
        }

        return tokens;
    }

    private Token<?> addSpaceToStringPart(Token<?> token) {
        if(token instanceof StringToken) {
            return new StringToken((String) token.getValue(), token.getStringPart() + " ");
        }

        if(token instanceof IntegerToken) {
            return new IntegerToken((Integer) token.getValue(), token.getStringPart() + " ");
        }

        if(token instanceof CharacterToken) {
            return new CharacterToken((Character) token.getValue(), token.getStringPart() + " ");
        }

        throw new IllegalArgumentException(
                "The type of the token must be StringToken, IntegerToken, or CharacterToken"
        );
    }
}
