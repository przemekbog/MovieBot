package com.pbo.movieBot.command.base;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User.UserFlag;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public abstract class Command<T> {
    protected String name = "NULL";
    protected List<String> aliases = new ArrayList<>();
    protected List<Permission> requiredPermissions = new ArrayList<>();
    protected String help = "No help available for this command";

    protected abstract void run(CommandEvent<T> event);

    public void execute(CommandEvent<T> event) {
        // TODO: Add authorisation and stuff... idk

        EnumSet<Permission> usersPermissions = event.getMember().getPermissions();
        for(Permission flag : requiredPermissions) {
            if(!usersPermissions.contains(flag)) {
                event.getChannel()
                        .sendMessage("You don't have permissions to use this command")
                        .queue();
                return;
            }
        }

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
}
