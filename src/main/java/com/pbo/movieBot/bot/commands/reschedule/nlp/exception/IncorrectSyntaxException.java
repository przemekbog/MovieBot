package com.pbo.movieBot.bot.commands.reschedule.nlp.exception;

public class IncorrectSyntaxException extends RuntimeException {
    public IncorrectSyntaxException(String message) {
        super(message);
    }

    public IncorrectSyntaxException() {
        super();
    }
}
