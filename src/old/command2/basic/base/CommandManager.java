package com.pbo.movieBot.command2.basic.base;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface CommandManager {
    void processCommand(GuildMessageReceivedEvent event, String commandWithoutPrefix);
    void setExternalManager(CommandManager externalManager);
    void removeExternalManager();
}
