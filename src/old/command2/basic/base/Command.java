package com.pbo.movieBot.command2.basic.base;

import com.pbo.movieBot.command2.basic.CommandInfoImpl;
import org.jetbrains.annotations.NotNull;

public abstract class Command {
    protected final String name;
    protected String[] aliases;
    protected CommandInfo info = new CommandInfoImpl();

    public Command(@NotNull String name) {
        this.name = name;
    }

    public abstract void execute(@NotNull CommandEvent event);

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        if(aliases == null) {
            return new String[] {};
        }
        return aliases;
    }

    public CommandInfo getInfo() {
        return info;
    }
}
