package com.pbo.movieBot.commands.help;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandExecutor;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommandListMessageCreator {
    private static final Character DOT_CHARACTER = '\u2022';

    public static Message getCommandListMessage(List<Command> commands) {
        List<Command> sortedCommands = sortCommands(commands);
        MessageBuilder builder = new MessageBuilder();

        builder.appendCodeBlock(getBigHelpText(), "txt");
        for(Command command : sortedCommands) {
            builder.append(getShortHelpFromCommand(command));
        }

        return builder.build();
    }

    private static String getShortHelpFromCommand(Command command) {
        String commandName = command.getName();
        String shortInfo = command.getHelp().getShortInfo();
        return DOT_CHARACTER + "  `" + commandName + "` - " + shortInfo + System.lineSeparator();
    }

    private static List<Command> sortCommands(List<Command> commands) {
        ArrayList<Command> copy = new ArrayList<>(commands);
        copy.sort(Comparator.comparing(Command::getName));
        return copy;
    }

    private static String getBigHelpText() {
        return  " _____ _____ __    _____ \n" +
                "|  |  |   __|  |  |  _  |\n" +
                "|     |   __|  |__|   __|\n" +
                "|__|__|_____|_____|__|   ";
    }
}
