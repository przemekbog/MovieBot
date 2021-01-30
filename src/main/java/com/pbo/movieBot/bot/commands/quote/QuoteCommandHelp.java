package com.pbo.movieBot.bot.commands.quote;

import com.pbo.movieBot.command.base.CommandHelp;

import java.util.List;

public class QuoteCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of("quote");
    }

    @Override
    public String getShortInfo() {
        return "gives a random quote";
    }

    @Override
    public String getDetailedInfo() {
        return "gives a random quote from The Clockwork Orange";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of("quote");
    }
}
