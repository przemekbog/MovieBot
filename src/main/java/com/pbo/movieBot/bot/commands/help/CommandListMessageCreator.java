package com.pbo.movieBot.bot.commands.help;

import com.pbo.movieBot.command.base.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommandListMessageCreator {

    public static Message getCommandListMessage(List<Command> commands) {
        MessageBuilder builder = new MessageBuilder();
        builder.setEmbed(getCommandListEmbed(commands));

        return builder.build();
    }

    private static MessageEmbed getCommandListEmbed(List<Command> commands) {
        List<Command> sortedCommands = sortCommands(commands);
        EmbedBuilder builder = new EmbedBuilder();

        builder.setDescription(getBigHelpText());
        for(Command command : sortedCommands) {
            builder.addField(command.getName(), command.getHelp().getShortInfo(), false);
        }

        return builder.build();
    }

    private static List<Command> sortCommands(List<Command> commands) {
        ArrayList<Command> copy = new ArrayList<>(commands);
        copy.sort(Comparator.comparing(Command::getName));
        return copy;
    }

    private static String getBigHelpText() {
        return """
                ```
                 _____ _____ __    _____
                |  |  |   __|  |  |  _  |
                |     |   __|  |__|   __|
                |__|__|_____|_____|__|                                  \s
                ```
                """;
    }
}
