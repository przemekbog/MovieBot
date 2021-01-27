package com.pbo.movieBot.movieApi.movie;

import java.util.HashMap;
import java.util.Map;

public enum PlotLength {
    SHORT("short"),
    FULL("full");


    private static Map<String, PlotLength> nameToPlotLengthMap = new HashMap<>();

    private String name;

    static {
        for(PlotLength movieType : PlotLength.values()) {
            nameToPlotLengthMap.put(movieType.getAsString(), movieType);
        }
    }

    PlotLength(String type) {
        this.name = type;
    }

    public static PlotLength fromString(String name) {
        return nameToPlotLengthMap.get(name);
    }

    public String getAsString() {
        return name;
    }
}
