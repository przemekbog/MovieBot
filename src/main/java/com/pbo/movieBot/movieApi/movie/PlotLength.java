package com.pbo.movieBot.movieApi.movie;

import java.util.HashMap;
import java.util.Map;

public enum PlotLength {
    SHORT("short"),
    FULL("full");

    private static Map<String, PlotLength> nameToPlotTypeMap = new HashMap<>();

    private String name;

    static {
        for(PlotLength plotLength : PlotLength.values()) {
            nameToPlotTypeMap.put(plotLength.getName(), plotLength);
        }
    }

    PlotLength(String name) {
        this.name = name;
    }

    public static PlotLength byName(String name) {
        return nameToPlotTypeMap.get(name);
    }

    public String getName() {
        return name;
    }
}
