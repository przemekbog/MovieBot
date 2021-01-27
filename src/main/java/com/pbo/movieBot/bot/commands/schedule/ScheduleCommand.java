package com.pbo.movieBot.bot.commands.schedule;

import com.pbo.movieBot.bot.utils.MovieInfoEmbedFactory;
import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.bot.context.MovieBotContext;
import com.pbo.movieBot.bot.commands.schedule.nlp.SchedulingPipeline;
import com.pbo.movieBot.bot.commands.schedule.nlp.exception.InvalidInputFormatException;
import com.pbo.movieBot.bot.commands.schedule.nlp.exception.InvalidMovieTitleException;
import com.pbo.movieBot.command.base.CommandHelp;
import com.pbo.movieBot.movieApi.MovieFetcher;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieApi.movie.PlotLength;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.LocalDateTime;

public class ScheduleCommand extends Command<MovieBotContext> {

    private static final Color SCHEDULING_COLOR = new Color(0xBA7E0E);

    @Override
    public String getName() {
        return "schedule";
    }

    @Override
    public CommandHelp getHelp() {
        return new ScheduleCommandHelp();
    }

    @Override
    public void execute(CommandEvent event, MovieBotContext context) {
        try {
            SchedulingPipeline pipeline = new SchedulingPipeline();
            MovieReservation reservation = pipeline.process(event.getArgs());

            if(!isReservationDateValid(reservation)) {
                sendIncorrectDateMessage(event);
                return;
            }

            context.addReservation(reservation);
            sendConfirmationMessage(event, reservation);
        } catch(InvalidInputFormatException e) {
            sendInvalidInputMessage(event);
        } catch (InvalidMovieTitleException e) {
            sendInvalidMovieTitleMessage(event);
        }
    }

    private void sendConfirmationMessage(CommandEvent event, MovieReservation reservation) {
        EmbedBuilder builder = new EmbedBuilder();

        String title = reservation.getTitle();
        Movie movie = MovieFetcher
                .withMovieTitle(title)
                .setPlotLength(PlotLength.SHORT)
                .fetch();

        builder.setDescription(getBookedString())
                .setColor(SCHEDULING_COLOR)
                .addField("Title", reservation.getTitle(), true)
                .addField("Date", reservation.getReservationDate().toString(), true)
                .addField("Time", reservation.getReservationTime().toString(), true);

        MessageEmbed reservationEmbed = builder.build();
        MessageEmbed infoEmbed = new MovieInfoEmbedFactory().getShortInfo(movie, SCHEDULING_COLOR);

        MessageChannel channel = event.getChannel();
        channel.sendMessage(reservationEmbed).queue();
        channel.sendMessage(infoEmbed).queue();
    }

    private String getBookedString() {
        return """
                ```
                                                      \s
                 _____ _____ _____ _____ _____ ____   \s
                | __  |     |     |  |  |   __|    \\  \s
                | __ -|  |  |  |  |    -|   __|  |  |_\s
                |_____|_____|_____|__|__|_____|____/|_|
                                                                                          \s
                ```
                """;
    }

    private boolean isReservationDateValid(MovieReservation reservation) {
        LocalDateTime reservationDateTime = LocalDateTime.of(
                reservation.getReservationDate(),
                reservation.getReservationTime()
        );

        LocalDateTime now = LocalDateTime.now();
        return reservationDateTime.isAfter(now);
    }

    private void sendIncorrectDateMessage(CommandEvent event) {
        event.getChannel().sendMessage("The date provided is incorrect.").queue();
    }

    private void sendInvalidMovieTitleMessage(CommandEvent event) {
        event.getChannel().sendMessage("The movie you are scheduling does not exist").queue();
    }

    private void sendInvalidInputMessage(CommandEvent event) {
        event.getChannel().sendMessage("Syntax error. Rephrase the command and try again").queue();
    }
}
