package com.pbo.movieBot.bot.commands.cancel;

import com.pbo.movieBot.bot.commands.cancel.nlp.CancelPipeline;
import com.pbo.movieBot.bot.context.MovieBotContext;
import com.pbo.movieBot.bot.utils.Emoji;
import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandHelp;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.filtering.Specification;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CancelCommand extends Command<MovieBotContext> {
    @Override
    public String getName() {
        return "cancel";
    }

    @Override
    public CommandHelp getHelp() {
        return new CancelCommandHelp();
    }

    @Override
    public void execute(CommandEvent event, MovieBotContext context) {
        Optional<MovieReservation> optionalMovieReservation = findFirstReservationSpecified(event.getArgs(), context);

        if(!optionalMovieReservation.isPresent()) {
            sendReservationNotFound(event);
            return;
        }

        MovieReservation deletedReservation = optionalMovieReservation.get();
        context.removeReservation(optionalMovieReservation.get());
        sendConfirmation(event, deletedReservation);
    }

    private Optional<MovieReservation> findFirstReservationSpecified(String args, MovieBotContext context) {
        CancelPipeline pipeline = new CancelPipeline();
        Specification<MovieReservation> specification = pipeline.process(args);

        List<MovieReservation> reservations = context.getMoviesBySpecification(specification);
        reservations.sort(Comparator.comparing(MovieReservation::getReservationDateTime));

        if(reservations.size() == 0) {
            return Optional.empty();
        }

        MovieReservation first = reservations.get(0);
        return Optional.of(first);
    }

    private void sendConfirmation(CommandEvent event, MovieReservation reservation) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setDescription(getCanceledString())
                .addField("Title", reservation.getTitle(), true)
                .addField("Date", reservation.getReservationDate().toString(), true)
                .addField("Time", reservation.getReservationTime().toString(), true);

        MessageEmbed embed = builder.build();

        event.getChannel().sendMessage(embed).queue();
    }

    private void sendReservationNotFound(CommandEvent event) {
        event.getMessage().addReaction(Emoji.RAGE).queue();
        event.getChannel().sendMessage("Reservation not found").queue();
    }

    private String getCanceledString() {
        return """
                ```
                                                                  \s
                 _____ _____ _____ _____ _____ __    _____ ____   \s
                |     |  _  |   | |     |   __|  |  |   __|    \\  \s
                |   --|     | | | |   --|   __|  |__|   __|  |  |_\s
                |_____|__|__|_|___|_____|_____|_____|_____|____/|_|
                                                                  \s
                ```
                """;
    }
}
