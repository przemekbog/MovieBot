package com.pbo.movieBot.commands.help;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandExecutor;
import com.pbo.movieBot.command.base.CommandHelp;
import com.pbo.movieBot.emoji.Emoji;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class HelpCommand extends Command {
    private static final String OK_HAND = Emoji.OK_HAND.getText();

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public CommandHelp getHelp() {
        return new HelpCommandHelp();
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        Message message;

        if(event.getArgs().equals("")) {
            message = getAllCommandInfo(event);
        } else {
            message = getDetailedInfo(event);
        }

        sendPrivateMessage(event.getAuthor(), message);
        event.getMessage().addReaction(OK_HAND).queue();
    }

    private Message getAllCommandInfo(CommandEvent event) {
        CommandExecutor executor = event.getCommandExecutor();
        return CommandListMessageCreator.getCommandListMessage(executor.getCommands());
    }

    private Message getDetailedInfo(CommandEvent event) {
        List<Command> commands = event.getCommandExecutor().getCommands();
        String name = event.getArgs();
        Optional<Command> commandOptional = findCommandWithName(commands, name);

        if(!commandOptional.isPresent()) {
            return new MessageBuilder().append("Command not found " + Emoji.DISAPPOINTED_FACE.getText()).build();
        }

        Command command = commandOptional.get();
        return DetailedHelpMessageCreator.getDetailedInfoFromCommand(command);
    }

    private void sendPrivateMessage(User user, Message message) {
        user.openPrivateChannel().queue(privateChannel ->
                privateChannel.sendMessage(message).queue()
        );
    }

    private Optional<Command> findCommandWithName(List<Command> commands, String name) {
        return commands.stream()
                .filter(command -> command.getName().equals(name))
                .findAny();
    }

}
