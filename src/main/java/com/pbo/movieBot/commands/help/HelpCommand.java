package com.pbo.movieBot.commands.help;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandExecutor;
import com.pbo.movieBot.emoji.Emoji;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.*;

public class HelpCommand extends Command {
    private static final Character DOT_CHARACTER = '\u2022';
    private static final String OK_HAND = Emoji.OK_HAND.getText();

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        String message = "";

        if(event.getArgs().equals("")) {
            message = getAllCommandInfo(event);
        } else {
            message = getDetailedInfo(event);
        }

        sendPrivateMessage(event, message);
        event.getMessage().addReaction(OK_HAND).queue();
    }

    private String getAllCommandInfo(CommandEvent event) {
        CommandExecutor executor = event.getCommandExecutor();
        List<Command> commands = sortCommands(executor.getCommands());
        StringBuilder builder = new StringBuilder(getBigHelpText());

        for(Command command : commands) {
            builder.append(getShortHelpFromCommand(command));
        }

        return builder.toString();
    }

    private String getDetailedInfo(CommandEvent event) {
        MessageChannel channel = event.getChannel();
        // TODO: Implement

        throw new IllegalStateException("Not implemented yet");
    }

    private List<Command> sortCommands(List<Command> commands) {
        ArrayList<Command> copy = new ArrayList<>(commands);
        copy.sort(Comparator.comparing(Command::getName));
        return copy;
    }

    private String getShortHelpFromCommand(Command command) {
        String commandName = command.getName();
        String shortInfo = command.getHelp().getShortInfo();
        return DOT_CHARACTER + "  `" + commandName + "` - " + shortInfo + System.lineSeparator();
    }

    private void sendPrivateMessage(CommandEvent event, String message) {
        event.getAuthor().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage(message).queue();
        });
    }

    private String getBigHelpText() {
        return  "```" +
                " _____ _____ __    _____ \n" +
                "|  |  |   __|  |  |  _  |\n" +
                "|     |   __|  |__|   __|\n" +
                "|__|__|_____|_____|__|   " +
                "```";
    }

}
