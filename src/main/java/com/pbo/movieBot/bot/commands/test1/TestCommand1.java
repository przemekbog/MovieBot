package com.pbo.movieBot.bot.commands.test1;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;

public class TestCommand1 extends Command {
    @Override
    public String getName() {
        return "test1";
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        event.getChannel().sendMessage("Test!").queue();
    }
}
