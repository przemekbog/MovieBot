package com.pbo.movieBot.command.base;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CommandEvent {
    private MessageReceivedEvent event;
    private String commandName;
    private String args;

    public CommandEvent(MessageReceivedEvent event, String command, String args) {
        this.event = event;
        this.commandName = command;
        this.args = args;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getArgs() {
        return args;
    }

    @Nonnull
    public Message getMessage() {
        return event.getMessage();
    }

    @Nonnull
    public User getAuthor() {
        return event.getAuthor();
    }

    @Nullable
    public Member getMember() {
        return event.getMember();
    }

    public boolean isWebhookMessage() {
        return event.isWebhookMessage();
    }

    @Nonnull
    public MessageChannel getChannel() {
        return event.getChannel();
    }

    @Nonnull
    public String getMessageId() {
        return event.getMessageId();
    }

    public long getMessageIdLong() {
        return event.getMessageIdLong();
    }

    public boolean isFromType(@NotNull ChannelType type) {
        return event.isFromType(type);
    }

    public boolean isFromGuild() {
        return event.isFromGuild();
    }

    @Nonnull
    public ChannelType getChannelType() {
        return event.getChannelType();
    }

    @Nonnull
    public Guild getGuild() {
        return event.getGuild();
    }

    @Nonnull
    public TextChannel getTextChannel() {
        return event.getTextChannel();
    }

    @Nonnull
    public PrivateChannel getPrivateChannel() {
        return event.getPrivateChannel();
    }

    @Nonnull
    public JDA getJDA() {
        return event.getJDA();
    }

    public long getResponseNumber() {
        return event.getResponseNumber();
    }
}
