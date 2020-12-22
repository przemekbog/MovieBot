package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandExecutor;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorImpl<T> implements CommandExecutor {
    private Map<String, Command<T>> commandMap = new HashMap<>();
    private T context;

    public CommandExecutorImpl(T context) {
        this.context = context;
    }

    @Override
    public void execute(CommandEvent event) {
        String name = event.getCommandName();

        if(commandMap.containsKey(name)) {
            Command<T> command = commandMap.get(name);
            command.execute(event, context);
        } else {
            sendCommandNotFoundMessage(event.getChannel());
        }
    }

    public void addCommands(Command<T> first, Command<T>... other) {
        addCommand(first);
        for(Command<T> command : other) {
            addCommand(command);
        }
    }

    public void addCommand(Command<T> command) {
        commandMap.put(command.getName(), command);

        String[] aliases = command.getAliases();
        if(aliases.length != 0) {
            addByAliases(command);
        }
    }

    private void sendCommandNotFoundMessage(MessageChannel channel) {
        channel.sendMessage("Command not found :(").queue();
    }

    private void addByAliases(Command<T> command) {
        String[] aliases = command.getAliases();
        for(String alias : aliases) {
            commandMap.put(alias, command);
        }
    }
}
