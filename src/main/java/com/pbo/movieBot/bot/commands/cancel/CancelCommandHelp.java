package com.pbo.movieBot.bot.commands.cancel;

import com.pbo.movieBot.command.base.CommandHelp;

import java.util.List;

public class CancelCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of(
                "cancel [title]",
                "cancel [title] [date]",
                "cancel [title] [time]",
                "cancel [title] [date and time]"
        );
    }

    @Override
    public String getShortInfo() {
        return "cancels a reservation";
    }

    @Override
    public String getDetailedInfo() {
        return "Cancels specified reservation. If there is more than one reservation " +
                "that matches what the user specified, the earliest one will be chosen.";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of(
                "cancel The Room",
                "cancel Bee Movie at 4:20",
                "cancel Bee Movie on 24.12.2099",
                "cancel Epic Movie on 01.01.2044"
        );
    }
}
