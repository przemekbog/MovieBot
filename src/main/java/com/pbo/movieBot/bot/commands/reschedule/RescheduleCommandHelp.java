package com.pbo.movieBot.bot.commands.reschedule;

import com.pbo.movieBot.command.base.CommandHelp;

import java.util.List;

public class RescheduleCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of(
                "reschedule [title] to [date and time]",
                "reschedule [title] from [date] to [date and time]",
                "reschedule [title] from [date and time] to [date and time]"
        );
    }

    @Override
    public String getShortInfo() {
        return "reschedules a movie reservation";
    }

    @Override
    public String getDetailedInfo() {
        return "Reschedules a movie reservation to some other date" +
                "and time. When the details about a rescheduled movie " +
                "overlap with other movies, the earliest one will be chosen.";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of(
                "reschedule Once Upon a Time in Hollywood to 31.12.2099 at 15:00",
                "reschedule Shrek from today to this time tomorrow",
                "reschedule Megamind from today at 5pm to tomorrow at 6pm"
        );
    }
}
