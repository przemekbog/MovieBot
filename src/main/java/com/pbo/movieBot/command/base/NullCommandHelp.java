package com.pbo.movieBot.command.base;

public class NullCommandHelp implements CommandHelp {
    @Override
    public String getSyntax() {
        return "N/A";
    }

    @Override
    public String getShortHelp() {
        return "N/A";
    }

    @Override
    public String getDetailedHelp() {
        return "N/A";
    }
}
