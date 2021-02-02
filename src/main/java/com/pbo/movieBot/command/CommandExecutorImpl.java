package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandExecutor;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandExecutorImpl<T> implements CommandExecutor {
    private Log log = LogFactory.getLog(CommandExecutorImpl.class);
    private Map<String, Command<T>> commandMap = new HashMap<>();
    private T context;

    public CommandExecutorImpl(T context) {
        this.context = context;
    }

    @Override
    public void execute(CommandEvent event) {
        String name = event.getCommandName();

        logCommandExecutionAttempt(event);
        if(commandMap.containsKey(name)) {
            Command<T> command = commandMap.get(name);
            logCommandFound(event);
            command.execute(event, context);
        } else {
            logCommandNotFound(event);
            sendCommandNotFoundMessage(event);
        }
    }

    @Override
    public List<Command> getCommands() {
        return new ArrayList<>(commandMap.values());
    }

    public void addCommands(Command<T> first, Command<T>... other) {
        addCommand(first);
        for(Command<T> command : other) {
            addCommand(command);
        }
    }

    public void addCommand(Command<T> command) {
        commandMap.put(command.getName(), command);
        addByAliases(command);
    }

    private void logCommandExecutionAttempt(CommandEvent event) {
        String authorsNick = event.getAuthor().getAsTag();
        String logMessage = String.format(
                "%s tried issuing \"%s\" command with arguments \"%s\"",
                authorsNick, event.getCommandName(), event.getArgs());

        log.info(logMessage);
    }

    private void logCommandFound(CommandEvent event) {
        String logMessage = String.format("Executing \"%s\" command...", event.getCommandName());
        log.info(logMessage);
    }

    private void logCommandNotFound(CommandEvent event) {
        String logMessage = String.format("Could not find the \"%s\" command.", event.getCommandName());
        log.error(logMessage);
    }

    private void sendCommandNotFoundMessage(CommandEvent event) {
        event.getChannel().sendMessage("Command not found :(").queue();
    }

    private void addByAliases(Command<T> command) {
        List<String> aliases = command.getAliases();
        for(String alias : aliases) {
            commandMap.put(alias, command);
        }
    }
}
