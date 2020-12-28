package com.pbo.movieBot.command.base;

import java.util.List;

public interface CommandExecutor {
    void execute(CommandEvent event);
    List<Command> getCommands();
}
