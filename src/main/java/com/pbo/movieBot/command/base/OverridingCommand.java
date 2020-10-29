package com.pbo.movieBot.command.base;

public abstract class OverridingCommand<T> extends Command<T> {
    private CommandManager manager;

    @Override
    public void execute(CommandEvent<T> event) {
        super.execute(event);
    }
}
