package com.pbo.movieBot.bot.commands.smile;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;

public class SmileCommand extends Command {

    @Override
    public String getName() {
        return ":)";
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        if(!event.getArgs().trim().equals("")) {
            event.getChannel().sendMessage("This command does not accept arguments.").queue();
            return;
        }

        event.getChannel().sendMessage("Command found :)").queue();
    }
}
