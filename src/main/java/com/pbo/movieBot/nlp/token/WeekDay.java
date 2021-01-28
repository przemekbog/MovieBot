package com.pbo.movieBot.nlp.token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum WeekDay {
    MONDAY("Monday", "Mon"),
    TUESDAY("Tuesday", "Tue"),
    WEDNESDAY("Wednesday", "Wed"),
    THURSDAY("Thursday", "Thu"),
    FRIDAY("Friday", "Fri"),
    SATURDAY("Saturday", "Sat"),
    SUNDAY("Sunday", "Sun");

    private static Map<String, WeekDay> stringWeekDayMap = new HashMap<>();
    private List<String> forms;

    static {
        for(WeekDay day : WeekDay.values()) {
            for(String form : day.getForms()) {
                stringWeekDayMap.put(form.toUpperCase(), day);
            }
        }
    }

    WeekDay(String... forms) {
        this.forms = List.of(forms);
    }

    public static boolean isWeekDay(String s) {
        return stringWeekDayMap.containsKey(s.toUpperCase());
    }

    public static WeekDay fromString(String s) {
        return stringWeekDayMap.get(s.toUpperCase());
    }

    public List<String> getForms() {
        return forms;
    }
}
