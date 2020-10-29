package com.pbo.movieBot.command2.basic.base;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class CommandClient extends ListenerAdapter {

    private CommandManager commandManager;
    private String prefix;

    public CommandClient(CommandManager commandManager, String prefix) {
        this.commandManager = commandManager;
        this.prefix = prefix;
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().trim();

        if(message.length() < prefix.length()) {
            return;
        }

        String messagePrefix = message.substring(0, prefix.length());
        if(!messagePrefix.equals(prefix)) {
            return;
        }

        commandManager.processCommand(event, message.substring(prefix.length()));
    }
}
