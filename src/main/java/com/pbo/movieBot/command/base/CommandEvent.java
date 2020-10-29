package com.pbo.movieBot.command.base;

import com.pbo.movieBot.command.base.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CommandEvent<T> {
    private GuildMessageReceivedEvent messageReceivedEvent;
    private CommandManager commandManager;
    private T environmentObject;
    private String[] args;

    public CommandEvent(
            GuildMessageReceivedEvent messageReceivedEvent,
            CommandManager commandManager,
            T environmentVariables,
            String[] command) {
        this.messageReceivedEvent = messageReceivedEvent;
        this.commandManager = commandManager;
        this.environmentObject = environmentVariables;
        this.args = command;
    }

    public GuildMessageReceivedEvent getMessageReceivedEvent() {
        return messageReceivedEvent;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public T getEnvironmentObject() {
        return environmentObject;
    }

    public String[] getArgs() {
        return args;
    }

    @Nonnull
    public Message getMessage() {
        return messageReceivedEvent.getMessage();
    }

    @Nonnull
    public User getAuthor() {
        return messageReceivedEvent.getAuthor();
    }

    @Nullable
    public Member getMember() {
        return messageReceivedEvent.getMember();
    }

    public boolean isWebhookMessage() {
        return messageReceivedEvent.isWebhookMessage();
    }

    @Nonnull
    public String getMessageId() {
        return messageReceivedEvent.getMessageId();
    }

    public long getMessageIdLong() {
        return messageReceivedEvent.getMessageIdLong();
    }

    @Nonnull
    public TextChannel getChannel() {
        return messageReceivedEvent.getChannel();
    }

    @Nonnull
    public Guild getGuild() {
        return messageReceivedEvent.getGuild();
    }

    @Nonnull
    public JDA getJDA() {
        return messageReceivedEvent.getJDA();
    }

    public long getResponseNumber() {
        return messageReceivedEvent.getResponseNumber();
    }
}
