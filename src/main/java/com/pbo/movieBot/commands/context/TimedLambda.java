package com.pbo.movieBot.commands.context;

import java.util.function.Consumer;

public class TimedLambda<TArgs> extends TimedEvent<TArgs> {
    private Consumer<TArgs> lambda;

    public TimedLambda(TArgs args, Consumer<TArgs> lambda) {
        super(args);
        this.lambda = lambda;
    }

    @Override
    protected void run(TArgs args) {
        lambda.accept(args);
    }
}
