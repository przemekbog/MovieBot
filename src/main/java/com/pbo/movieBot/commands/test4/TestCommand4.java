package com.pbo.movieBot.commands.test4;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;

public class TestCommand4 extends Command {
    @Override
    public String getName() {
        return "test4";
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        event.getChannel().sendMessage("XD").queue();
    }
}
