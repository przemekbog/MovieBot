package com.pbo.movieBot.command.base;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public interface CommandManager<T> {
    void execute(GuildMessageReceivedEvent event, String commandWithoutPrefix);
    void overrideBehaviorTo(@NotNull CommandManager manager);
    void stopOverride();
    boolean isOverriden();
    CommandManager getOverridingManager();
}
