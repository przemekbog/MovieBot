package com.pbo.movieBot.bot.commands.help;

import com.pbo.movieBot.command.base.CommandHelp;

import java.util.List;

public class HelpCommandHelp implements CommandHelp {
    @Override
    public List<String> getSyntaxList() {
        return List.of(
                "help",
                "help [command]"
        );
    }

    @Override
    public String getShortInfo() {
        return "see what a certain command does";
    }

    @Override
    public String getDetailedInfo() {
        return "Command for getting information about other commands. " +
                "You can either check what commands are available (example 1), " +
                "or get detailed info about a certain command (example 2).";
    }

    @Override
    public List<String> getUseExamples() {
        return List.of(
                "help",
                "help help"
        );
    }
}
