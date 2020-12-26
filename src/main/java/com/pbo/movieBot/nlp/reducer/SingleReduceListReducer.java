package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.ListReducer;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;

import java.util.ArrayList;
import java.util.List;

public class SingleReduceListReducer implements ListReducer {
    private Reducer<?> reducer;
    private SearchMode mode;

    public SingleReduceListReducer(Reducer<?> reducer, SearchMode mode) {
        this.reducer = reducer;
        this.mode = mode;
    }

    @Override
    public List<Token<?>> reduceList(List<Token<?>> tokens) {
        return singleReduce(tokens, mode);
    }

    private List<Token<?>> singleReduce(List<Token<?>> tokens, SearchMode mode) {

        int matchStart;
        if(mode == SearchMode.FROM_BEGINNING) {
            matchStart = findMatchFromBeginning(tokens);
        } else {
            matchStart = findMatchFromEnd(tokens);
        }

        int matchEnd = matchStart + reducer.getPattern().getTokenCount();
        if(matchStart == -1) {
            return tokens;
        }

        ArrayList<Token<?>> reduced = new ArrayList<>();

        reduced.addAll(tokens.subList(0, matchStart));
        List<Token<?>> match = tokens.subList(matchStart, matchEnd);
        reduced.add(reducer.reduce(match));
        reduced.addAll(tokens.subList(matchEnd, tokens.size()));

        return reduced;
    }

    private int findMatchFromBeginning(List<Token<?>> tokens) {
        Pattern pattern = reducer.getPattern();
        int patternSize = pattern.getTokenCount();

        for(int i = 0; i < tokens.size() - patternSize; i++) {
            List<Token<?>> part = tokens.subList(i, i + patternSize);

            if (pattern.matches(part)) {
                return i;
            }
        }

        return -1;
    }

    private int findMatchFromEnd(List<Token<?>> tokens) {
        Pattern pattern = reducer.getPattern();
        int patternSize = pattern.getTokenCount();

        for(int i = tokens.size() - patternSize; i >= 0; i--) {
            List<Token<?>> part = tokens.subList(i, i + patternSize);

            if (pattern.matches(part)) {
                return i;
            }
        }

        return -1;
    }


    public enum SearchMode {
        FROM_BEGINNING,
        FROM_END
    }
}
