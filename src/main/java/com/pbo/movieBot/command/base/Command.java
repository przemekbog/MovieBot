package com.pbo.movieBot.command.base;

import java.util.ArrayList;
import java.util.List;

public abstract class Command<T> {
    protected String name = "NULL";
    protected List<String> aliases = new ArrayList<>();
//    protected List<Permission> requiredPermissions = new ArrayList<>();
    protected String help = "No help available for this command";

    public void execute(CommandEvent<T> event) {
        // TODO: Add authorisation and stuff... idk
        run(event);
    }

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getHelp() {
        return help;
    }

    protected abstract void run(CommandEvent<T> event);
}
