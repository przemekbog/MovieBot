package com.pbo.movieBot.command.base;

public interface ModifiableManager<T> extends CommandManager<T> {
    void addCommand(Command<T> command);
    void addCommands(Command<T> first, Command<T>... other);
}
