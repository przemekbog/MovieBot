package com.pbo.movieBot.nlp.pattern;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListPattern implements Pattern {
    private List<Pattern> patterns;
    private Integer cachedTokenCount;

    public ListPattern(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public ListPattern(Pattern first, Pattern... other) {
        ArrayList<Pattern> patterns = new ArrayList<>();
        patterns.add(first);
        patterns.addAll(Arrays.asList(other));

        this.patterns = patterns;
    }

    @Override
    public int getTokenCount() {
        if(cachedTokenCount == null) {
            cachedTokenCount = calculateTokenCount();
        }

        return cachedTokenCount;
    }

    @Override
    public boolean matches(List<Token<?>> tokens) {
        if(tokens.size() != getTokenCount()) {
            return false;
        }

        int index = 0;
        for(Pattern pattern : patterns) {
            int tokenCount = pattern.getTokenCount();
            List<Token<?>> part = tokens.subList(index, index + tokenCount);

            if(!pattern.matches(part)) {
                return false;
            }

            index += tokenCount;
        }

        return true;
    }

    private int calculateTokenCount() {
        int sum = 0;

        for(Pattern pattern : patterns) {
            sum += pattern.getTokenCount();
        }

        return sum;
    }
}
