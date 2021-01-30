package com.pbo.movieBot.bot.commands.reschedule;

import com.pbo.movieBot.bot.commands.reschedule.nlp.ReschedulingPipeline;
import com.pbo.movieBot.bot.context.MovieBotContext;
import com.pbo.movieBot.bot.utils.Emoji;
import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandHelp;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.filtering.Specification;
import net.dv8tion.jda.internal.utils.tuple.Pair;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RescheduleCommand extends Command<MovieBotContext> {
    @Override
    public String getName() {
        return "reschedule";
    }

    @Override
    public CommandHelp getHelp() {
        return new RescheduleCommandHelp();
    }

    @Override
    public void execute(CommandEvent event, MovieBotContext context) {
        String text = event.getArgs();
        ReschedulingPipeline pipeline = new ReschedulingPipeline();

        Pair<Specification<MovieReservation>, MovieReservation> interpretedResult = pipeline.process(text);
        Specification<MovieReservation> specification = interpretedResult.getLeft();

        Optional<MovieReservation> optionalMovieReservation = getFirstReservationBySpecification(context, specification);

        if(!optionalMovieReservation.isPresent()) {
            sendMovieNotFound(event);
            return;
        }

        MovieReservation deletedReservation = optionalMovieReservation.get();
        MovieReservation replacementReservation = interpretedResult.getRight();

        if(!isReplacementDateValid(replacementReservation)) {
            sendInvalidDate(event);
            return;
        }

        context.removeReservation(deletedReservation);
        context.addReservation(replacementReservation);

        sendConfirmation(event);
    }

    private void sendInvalidDate(CommandEvent event) {
        event.getChannel().sendMessage("Invalid date " + Emoji.ANGRY).queue();
    }

    private void sendMovieNotFound(CommandEvent event) {
        event.getChannel().sendMessage("Could not find the specified movie " + Emoji.DISAPPOINTED_FACE).queue();
    }

    private void sendConfirmation(CommandEvent event) {
        event.getMessage().addReaction(Emoji.OK_HAND).queue();
    }

    private boolean isReplacementDateValid(MovieReservation reservation) {
        LocalDateTime dateTime = LocalDateTime.of(
                reservation.getReservationDate(),
                reservation.getReservationTime()
        );

        LocalDateTime now = LocalDateTime.now();
        return dateTime.isAfter(now);
    }

    private Optional<MovieReservation> getFirstReservationBySpecification(
            MovieBotContext context,
            Specification<MovieReservation> specification) {

        List<MovieReservation> reservations = context.getMoviesBySpecification(specification);

        if(reservations.size() == 0) {
            return Optional.empty();
        }

        reservations.sort(Comparator.comparing(MovieReservation::getReservationDateTime));
        MovieReservation first = reservations.get(0);
        return Optional.of(first);
    }
}
