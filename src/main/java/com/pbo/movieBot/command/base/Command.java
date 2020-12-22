package com.pbo.movieBot.command.base;

public abstract class Command<T> {
    public abstract String getName();
    public abstract void execute(CommandEvent event, T context);

    public String[] getAliases() {
        return new String[]{};
    }

    public CommandHelp getHelp() {
        return new NullCommandHelp(); // Default
    }
}
