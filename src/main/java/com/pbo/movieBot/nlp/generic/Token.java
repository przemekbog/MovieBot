package com.pbo.movieBot.nlp.generic;

public class Token<T> {
    protected T value;
    protected String stringPart;

    public Token(T value, String stringPart) {
        this.value = value;
        this.stringPart = stringPart;
    }

    public T getValue() {
        return value;
    }

    public Class<?> getValueClass() {
        return value.getClass();
    }

    public String getStringPart() {
        return value.toString();
    }

    public void accept(Visitor visitor) {
        if(visitor instanceof TokenVisitor) {
            ((TokenVisitor) visitor).visit(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!obj.getClass().equals(getClass())) {
            return false;
        }

        Token token = (Token) obj;
        if(!token.getValueClass().equals(getValueClass())) {
            return false;
        }

        T tokenValue = (T) token.getValue();
        if(tokenValue.equals(value)) {
            return true;
        } else {
            return false;
        }
    }
}
