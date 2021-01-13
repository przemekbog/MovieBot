package com.pbo.movieBot.commands.schedule;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.commands.MovieBotContext;
import com.pbo.movieBot.commands.schedule.nlp.SchedulingPipeline;
import com.pbo.movieBot.movieSaving.base.MovieReservation;

import java.util.List;

public class ScheduleCommand extends Command<MovieBotContext> {
    @Override
    public String getName() {
        return "schedule";
    }


    @Override
    public void execute(CommandEvent event, MovieBotContext context) {
        try {
            SchedulingPipeline pipeline = new SchedulingPipeline();
            MovieReservation reservation = pipeline.process(event.getArgs());
            event.getChannel().sendMessage(reservation.toString()).queue();
        } catch(IllegalArgumentException e) {
            sendIncorrectInputMessage(event);
        }
    }

    private void sendIncorrectInputMessage(CommandEvent event) {
        event.getChannel().sendMessage("You fucked something up, m8").queue();
    }
}
