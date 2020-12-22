package com.pbo.movieBot.commands;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;

public class TestCommand extends Command {
    @Override
    public String getName() {
        return "testCom";
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        event.getChannel().sendMessage("Test!").queue();
    }
}
