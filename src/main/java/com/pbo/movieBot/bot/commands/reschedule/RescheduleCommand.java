package com.pbo.movieBot.bot.commands.reschedule;

import com.pbo.movieBot.bot.commands.reschedule.nlp.ReschedulingPipeline;
import com.pbo.movieBot.bot.context.MovieBotContext;
import com.pbo.movieBot.bot.utils.Emoji;
import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.filtering.Specification;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.internal.utils.tuple.Pair;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RescheduleCommand extends Command<MovieBotContext> {
    @Override
    public String getName() {
        return "reschedule";
    }

    @Override
    public void execute(CommandEvent event, MovieBotContext context) {
        String text = event.getArgs();
        ReschedulingPipeline pipeline = new ReschedulingPipeline();

        Pair<Specification<MovieReservation>, MovieReservation> interpretedResult = pipeline.process(text);
        Specification<MovieReservation> specification = interpretedResult.getLeft();

        Optional<MovieReservation> optionalMovieReservation = getFirstReservationBySpecification(context, specification);

        if(!optionalMovieReservation.isPresent()) {
            sendNoMovieFoundMessage(event.getChannel());
            return;
        }

        MovieReservation deletedReservation = optionalMovieReservation.get();
        MovieReservation replacementReservation = interpretedResult.getRight();

        context.removeReservation(deletedReservation);
        context.addReservation(replacementReservation);

        event.getMessage().addReaction(Emoji.OK_HAND).queue();
        event.getChannel().sendMessage(deletedReservation.toString() + replacementReservation.toString()).queue();
    }

    private void sendNoMovieFoundMessage(MessageChannel channel) {
        channel.sendMessage("Could not find the specified movie " + Emoji.DISAPPOINTED_FACE).queue();
    }

    private Optional<MovieReservation> getFirstReservationBySpecification(
            MovieBotContext context,
            Specification<MovieReservation> specification) {

        List<MovieReservation> reservations = context.getBySpecification(specification);

        if(reservations.size() == 0) {
            return Optional.empty();
        }

        reservations.sort(Comparator.comparing(MovieReservation::getReservationDateTime));
        MovieReservation first = reservations.get(0);
        return Optional.of(first);
    }
}
