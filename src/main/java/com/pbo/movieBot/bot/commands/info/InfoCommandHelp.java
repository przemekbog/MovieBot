package com.pbo.movieBot.bot.commands.info;

import com.pbo.movieBot.command.base.CommandHelp;

import java.util.List;

public class InfoCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of("info [movie name]");
    }

    @Override
    public String getShortInfo() {
        return "shows info about a certain movie";
    }

    @Override
    public String getDetailedInfo() {
        return "Shows info about a certain movie in form of an embed. " +
                "The information being shown includes the title, release date, " +
                "film's director, runtime length, and more.";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of(
                "info Shark Tale",
                "info The Room",
                "info Once Upon a Time in Hollywood"
        );
    }
}
