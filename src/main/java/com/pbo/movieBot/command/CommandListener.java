package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandExecutor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CommandListener extends ListenerAdapter {

    private CommandExecutor executor;
    private String prefix;

    public CommandListener(CommandExecutor executor, String prefix) {
        this.executor = executor;
        this.prefix = prefix;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) {
            return;
        }

        String fullMessage = event.getMessage().getContentRaw().trim();
        String potentialPrefix = extractPotentialPrefix(fullMessage);
        if(!potentialPrefix.equals(prefix)) {
            return;
        }

        String commandName = extractCommandName(fullMessage);
        String commandArgs = extractCommandArgs(fullMessage);
        CommandEvent commandEvent = new CommandEvent(event, commandName, commandArgs);
        executor.execute(commandEvent);
    }

    private String extractPotentialPrefix(String message) {
        return message.substring(0, prefix.length());
    }

    private String extractCommandName(String message) {
        String[] messageParts = splitMessageBySpaces(message);
        return messageParts[0];
    }

    private String extractCommandArgs(String message) {
        String[] messageParts = splitMessageBySpaces(message);
        String[] args = subArray(messageParts, 1, messageParts.length);
        return String.join(" ", args);
    }

    private String[] subArray(String[] arr, int fromIndex, int toIndex) {
        return Arrays.asList(arr).subList(fromIndex, toIndex).toArray(new String[0]);
    }

    private String[] splitMessageBySpaces(String message) {
        String messageWithoutPrefix = message.substring(prefix.length());
        String[] messageParts = messageWithoutPrefix.split(" ");
        return messageParts;
    }
}
