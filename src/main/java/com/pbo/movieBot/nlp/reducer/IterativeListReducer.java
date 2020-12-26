package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.base.ListReducer;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;

import java.util.ArrayList;
import java.util.List;

public class IterativeListReducer implements ListReducer {
    private Reducer<?> reducer;

    public IterativeListReducer(Reducer<?> reducer) {
        this.reducer = reducer;
    }

    @Override
    public List<Token<?>> reduceList(List<Token<?>> tokens) {
        ArrayList<Token<?>> reducedList = new ArrayList<>();
        Pattern pattern = reducer.getPattern();
        int patternSize = pattern.getTokenCount();

        int index = 0;
        while(index < tokens.size() - patternSize) {
            List<Token<?>> part = tokens.subList(index, index + patternSize);

            if(pattern.matches(part)) {
                reducedList.add(reducer.reduce(part));
                index += patternSize;
            } else {
                reducedList.add(tokens.get(index));
                index++;
            }
        }

        List<Token<?>> remainingTokens = tokens.subList(index, tokens.size());
        reducedList.addAll(remainingTokens);

        return reducedList;
    }
}
