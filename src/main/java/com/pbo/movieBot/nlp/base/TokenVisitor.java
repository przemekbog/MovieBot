package com.pbo.movieBot.nlp.base;

public interface TokenVisitor extends Visitor {
    void visit(Token<?> token);
}
