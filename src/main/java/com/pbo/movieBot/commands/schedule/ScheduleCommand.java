package com.pbo.movieBot.commands.schedule;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.commands.MovieBotContext;

import java.util.List;

public class ScheduleCommand extends Command<MovieBotContext> {
    @Override
    public String getName() {
        return "schedule";
    }

    

    @Override
    public void execute(CommandEvent event, MovieBotContext context) {
        event.getChannel().sendMessage(".").queue();
    }
}
