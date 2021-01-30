package com.pbo.movieBot.bot.commands.announcementChannel;

import com.pbo.movieBot.command.base.CommandHelp;

import java.util.List;

public class AnnouncementChannelCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of("announcement_channel [channel mention]");
    }

    @Override
    public String getShortInfo() {
        return "changes the announcement channel";
    }

    @Override
    public String getDetailedInfo() {
        return "Changes the announcement channel to the one, " +
                "specified by the user.";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of("announcement_channel #announcements");
    }
}
