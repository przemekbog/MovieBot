package com.pbo.movieBot.nlp.pattern;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AndPattern implements Pattern {

    private List<Pattern> patterns;
    private int tokenCount;

    public AndPattern(Pattern first, Pattern... other) {
        ArrayList<Pattern> patterns = new ArrayList<>();
        patterns.add(first);
        patterns.addAll(Arrays.asList(other));

        setPatterns(patterns);
    }

    public AndPattern(List<Pattern> patterns) {
        setPatterns(patterns);
    }

    @Override
    public int getTokenCount() {
        return tokenCount;
    }

    @Override
    public boolean matches(List<Token<?>> tokens) {
        if(tokens.size() != getTokenCount()) {
            return false;
        }

        for(Pattern pattern : patterns) {
            if(!pattern.matches(tokens)) {
                return false;
            }
        }

        return true;
    }

    public void setPatterns(List<Pattern> patterns) {
        if(!arePatternsValid(patterns)) {
            throw new IllegalArgumentException(
                    "All patterns in a and pattern need to require the same amount of tokens"
            );
        }

        tokenCount = patterns.get(0).getTokenCount();
        this.patterns = patterns;
    }

    public boolean arePatternsValid(List<Pattern> patterns) {
        for (Pattern pattern : patterns) {
            int firstTokenCount = patterns.get(0).getTokenCount();

            if (pattern.getTokenCount() != firstTokenCount) {
                return false;
            }
        }

        return true;
    }
}
