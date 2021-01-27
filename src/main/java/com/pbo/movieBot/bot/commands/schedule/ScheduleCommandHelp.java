package com.pbo.movieBot.bot.commands.schedule;

import com.pbo.movieBot.command.base.CommandHelp;

import java.util.List;

public class ScheduleCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of("!schedule [title] for [date and time]");
    }

    @Override
    public String getShortInfo() {
        return "schedules when a certain movie will be announced.";
    }

    @Override
    public String getDetailedInfo() {
        return "Schedules when a movie, specified by the user, " +
                "will be announced. You can specify the date and " +
                "time either by using numbers number (example 1, 2, 4), " +
                "or words (example 5). ";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of(
                "!schedule Shrek for 5pm on 03.10.2020",
                "!schedule Emoji Movie for 13:00 on 15th December",
                "!schedule Pulp Fiction for 10am on 15th",
                "!schedule Inception for 20:00 tomorrow",
                "!schedule Castaway for this time tomorrow",
                "!schedule The Room for 10pm today"
        );
    }
}
