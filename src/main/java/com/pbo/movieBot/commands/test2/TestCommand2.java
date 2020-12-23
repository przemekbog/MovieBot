package com.pbo.movieBot.commands.test2;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import net.dv8tion.jda.api.entities.MessageChannel;

public class TestCommand2 extends Command {
    @Override
    public String getName() {
        return "test2";
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        String name = event.getCommandName();
        String args = event.getArgs();
        MessageChannel channel = event.getChannel();
        channel.sendMessage("Name: " + name).queue();
        channel.sendMessage("Args: " + args).queue();
    }
}
