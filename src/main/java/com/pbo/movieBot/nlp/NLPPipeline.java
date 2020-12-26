package com.pbo.movieBot.nlp;

import com.pbo.movieBot.nlp.base.*;

import java.util.ArrayList;
import java.util.List;

public class NLPPipeline<T> {
    private Tokenizer tokenizer;
    private List<ListReducer> reducers;
    private Parser<T> parser;

    public NLPPipeline() {

    }

    public NLPPipeline(Tokenizer tokenizer, List<ListReducer> reducers, Parser<T> parser) {
        this.tokenizer = tokenizer;
        this.reducers = reducers;
        this.parser = parser;
    }

    public void setTokenizer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void setListReducers(List<ListReducer> reducers) {
        this.reducers = reducers;
    }

    public void setListReducers(ListReducer... reducers) {
        this.reducers = List.of(reducers);
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }

    public T process(String s) {
        List<Token<?>> tokens = tokenizer.tokenize(s);

        for(ListReducer reducer : reducers) {
            tokens = reducer.reduceList(tokens);
        }

        return parser.parse(tokens);
    }
}
