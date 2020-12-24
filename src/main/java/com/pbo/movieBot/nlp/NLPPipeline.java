package com.pbo.movieBot.nlp;

import com.pbo.movieBot.nlp.base.*;

import java.util.ArrayList;
import java.util.List;

public class NLPPipeline<T> {
    private Tokenizer tokenizer;
    private List<Reducer<?>> reducers;
    private Parser<T> parser;

    public NLPPipeline() {

    }

    public NLPPipeline(Tokenizer tokenizer, List<Reducer<?>> reducers, Parser<T> parser) {
        this.tokenizer = tokenizer;
        this.reducers = reducers;
        this.parser = parser;
    }

    public void setTokenizer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void setReducers(List<Reducer<?>> reducers) {
        this.reducers = reducers;
    }

    public void setReducers(Reducer<?>... reducers) {
        this.reducers = List.of(reducers);
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }

    public T process(String s) {
        List<Token<?>> tokens = tokenizer.tokenize(s);

        for(Reducer reducer : reducers) {
            tokens = reduceList(reducer, tokens);
        }

        return parser.parse(tokens);
    }

    private List<Token<?>> reduceList(Reducer reducer, List<Token<?>> tokens) {
        ArrayList<Token<?>> reduced = new ArrayList<>();
        Pattern pattern = reducer.getPattern();
        int patternLength = pattern.getTokenCount();

        int i = 0;
        while(i < tokens.size()) {
            int partStartIndex = i;
            int partEndIndex = Math.min(i + patternLength, tokens.size());
            List part = tokens.subList(partStartIndex,  partEndIndex);

            if(pattern.matches(part)) {
                reduced.add(reducer.reduce(part));
                i += patternLength;
            } else {
                reduced.add(tokens.get(i));
                i++;
            }
        }

        return reduced;
    }
}
