package com.pbo.movieBot.bot.commands.reservations;

import com.pbo.movieBot.bot.context.MovieBotContext;
import com.pbo.movieBot.bot.utils.Emoji;
import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandHelp;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.filtering.AnySpecification;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ReservationsCommand extends Command<MovieBotContext> {

    private static final int RESERVATIONS_PER_PAGE = 10;
    private static final Color LIST_EMBED_COLOR = new Color(0xBA7E0E);
    private static final String BULLET_LIST_CHARACTER = "\u2022";

    @Override
    public String getName() {
        return "reservations";
    }

    @Override
    public CommandHelp getHelp() {
        return new ReservationsCommandHelp();
    }

    @Override
    public void execute(CommandEvent event, MovieBotContext context) {
        String args = event.getArgs();

        if(args.equals("")) {
            showReservationsPage(event, context, 1);
            return;
        }

        try {
            int pageNumber = Integer.parseInt(args);
            showReservationsPage(event, context, pageNumber);
        } catch (NumberFormatException e) {
            sendArgumentIsNotANumber(event);
        }
    }

    void showReservationsPage(CommandEvent event, MovieBotContext context, int pageNum) {
        int pageIndex = pageNum - 1;

        int lastPageIndex = getNumReservationPages(context) - 1;
        if(pageIndex > lastPageIndex) {
            pageIndex = lastPageIndex;
        }

        if(pageIndex < 0) {
            pageIndex = 0;
        }

        List<MovieReservation> reservations = getPageReservationsList(context, pageIndex);
        MessageEmbed listEmbed = getListOfReservationsEmbed(reservations, pageIndex);

        event.getChannel().sendMessage(listEmbed).queue();
    }

    private MessageEmbed getListOfReservationsEmbed(List<MovieReservation> reservations, int pageIndex) {
        EmbedBuilder builder = new EmbedBuilder();

        String embedTitle = String.format("Reservations (%s)", pageIndex + 1);
        builder.setTitle(embedTitle)
                .setColor(LIST_EMBED_COLOR)
                .addField("Title", getTitleColumn(reservations), true)
                .addField("Date", getDateColumn(reservations), true)
                .addField("Time", getTimeColumn(reservations), true);

        return builder.build();
    }

    private List<MovieReservation> getPageReservationsList(MovieBotContext context, int pageIndex) {
        if(pageIndex < 0) {
            return List.of();
        }

        List<MovieReservation> reservations = context.getMoviesBySpecification(new AnySpecification<>());

        reservations.sort(Comparator.comparing(MovieReservation::getReservationDateTime));

        int firstIndex = pageIndex * RESERVATIONS_PER_PAGE;
        int secondIndex = firstIndex + RESERVATIONS_PER_PAGE;

        int numberOfReservations = context.getNumberOfReservations();
        if(secondIndex > numberOfReservations) {
            secondIndex = numberOfReservations;
        }

        return reservations.subList(firstIndex, secondIndex);
    }

    private String getTitleColumn(List<MovieReservation> reservations) {
        return getColumnContent(
                reservations,
                reservation -> BULLET_LIST_CHARACTER + " *" + reservation.getTitle() + "*"
        );
    }

    private String getDateColumn(List<MovieReservation> reservations) {
        return getColumnContent(
                reservations,
                reservation -> reservation.getReservationDate().toString()
        );
    }

    private String getTimeColumn(List<MovieReservation> reservations) {
        return getColumnContent(
                reservations,
                reservation -> reservation.getReservationTime().toString()
        );
    }

    private String getColumnContent(List<MovieReservation> reservations,
                                    Function<MovieReservation, String> elementGenerator) {
        StringBuilder stringBuilder = new StringBuilder();

        for(MovieReservation reservation : reservations) {
            String element = elementGenerator.apply(reservation);
            stringBuilder.append(element + "\n");
        }

        return stringBuilder.toString();
    }


    void sendArgumentIsNotANumber(CommandEvent event) {
        event.getMessage().addReaction(Emoji.ANGRY).queue();
        event.getChannel().sendMessage("Argument must be empty or a number").queue();
    }

    private int getNumReservationPages(MovieBotContext context) {
        return context.getNumberOfReservations() / RESERVATIONS_PER_PAGE;
    }
}
