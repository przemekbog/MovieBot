package com.pbo.movieBot.command.base;

import java.util.List;

public class NullCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of();
    }

    @Override
    public String getShortInfo() {
        return "N/A";
    }

    @Override
    public String getDetailedInfo() {
        return "N/A";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of(/* emptiness */);
    }
}
