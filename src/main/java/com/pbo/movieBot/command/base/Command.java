package com.pbo.movieBot.command.base;

import java.util.List;

public abstract class Command<T> {
    public abstract String getName();
    public abstract void execute(CommandEvent event, T context);

    public List<String> getAliases() {
        return List.of();
    }

    public CommandHelp getHelp() {
        return new NullCommandHelp(); // Default
    }
}
