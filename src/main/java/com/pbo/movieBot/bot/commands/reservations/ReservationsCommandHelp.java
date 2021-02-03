package com.pbo.movieBot.bot.commands.reservations;

import com.pbo.movieBot.command.base.CommandHelp;

import java.util.List;

public class ReservationsCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of(
                "reservations",
                "reservations [page number]"
        );
    }

    @Override
    public String getShortInfo() {
        return "shows scheduled reservations";
    }

    @Override
    public String getDetailedInfo() {
        return "shows a list of scheduled reservations. " +
                "Because there may be a lot of scheduled movies, " +
                "the list is split into pages. You can see the page" +
                " you want by adding the page number after as an argument (example 2).";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of(
                "reservations",
                "reservations 3"
        );
    }
}
