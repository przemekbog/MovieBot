package com.pbo.movieBot.nlp;

public class OrdinalNumber {
    private int value;
    private String suffix;

    public OrdinalNumber(int value) {
        this.value = value;
        suffix = getSuffix(value);
    }

    public static String getSuffix(int n) {
        String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        return switch (n % 100) {
            case 11, 12, 13 -> "th";
            default -> suffixes[n % 10];
        };
    }

    public int getValue() {
        return value;
    }

    public String getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        return value + suffix;
    }
}
