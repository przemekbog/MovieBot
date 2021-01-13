package com.pbo.movieBot.commands.schedule;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.commands.MovieBotContext;
import com.pbo.movieBot.commands.schedule.nlp.SchedulingPipeline;
import com.pbo.movieBot.commands.schedule.nlp.exception.InvalidInputFormatException;
import com.pbo.movieBot.commands.schedule.nlp.exception.InvalidMovieTitleException;
import com.pbo.movieBot.movieSaving.base.MovieReservation;

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
            context.getSaver().add(reservation);
            System.out.println(context.getSaver().toString());
        } catch(InvalidInputFormatException e) {
            sendInvalidInputMessage(event);
        } catch (InvalidMovieTitleException e) {
            sendInvalidMovieTitleMessage(event);
        }
    }

    private void sendInvalidMovieTitleMessage(CommandEvent event) {
        event.getChannel().sendMessage("The movie you are scheduling does not exist").queue();
    }

    private void sendInvalidInputMessage(CommandEvent event) {
        event.getChannel().sendMessage("You fucked something up, m8").queue();
    }
}
