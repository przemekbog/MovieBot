package com.pbo.movieBot.nlp.generic;

public interface TokenVisitor extends Visitor {
    void visit(Token<?> token);
}
