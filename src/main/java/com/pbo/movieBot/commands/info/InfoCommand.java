package com.pbo.movieBot.commands.info;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandHelp;
import com.pbo.movieBot.emoji.Emoji;
import com.pbo.movieBot.movieApi.MovieFetcher;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieApi.movie.MovieRating;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class InfoCommand extends Command {
    private static final Color NICE_ORANGE = new Color(0xd49324);

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public CommandHelp getHelp() {
        return new InfoCommandHelp();
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        String title = event.getArgs();
        MovieFetcher fetcher = MovieFetcher.withMovieTitle(title);
        Movie movie = fetcher.setPlot("full").fetch();

        MessageChannel channel = event.getChannel();
        if(movie.getTitle().equals("N/A")) {
            sendMovieNotFoundMessage(channel);
            return;
        }

        MessageEmbed infoMessage = getInfoEmbedFromMovie(movie);
        channel.sendMessage(infoMessage).queue();
    }

    private void sendMovieNotFoundMessage(MessageChannel channel) {
        channel.sendMessage("Movie not found " + Emoji.DISAPPOINTED_FACE.getText()).queue();
    }

    private MessageEmbed getInfoEmbedFromMovie(Movie movie) {
        EmbedBuilder builder = new EmbedBuilder();

        setTitle(builder, movie);
        setColor(builder);
        appendPlot(builder, movie);
        appendInfo(builder, movie);
        appendRatings(builder, movie);
        setPoster(builder, movie);
        setFooter(builder);

        return builder.build();
    }

    private void setTitle(EmbedBuilder builder, Movie movie) {
        String title = movie.getTitle();
        String year = movie.getYear();
        String titleString = String.format("%s (%s)", title, year);

        String url = movie.getWebsite();
        if(url.equals("N/A")) {
            builder.setTitle(titleString);
        } else {
            builder.setTitle(titleString, url);
        }
    }

    private void setColor(EmbedBuilder builder) {
        builder.setColor(NICE_ORANGE);
    }

    private void appendPlot(EmbedBuilder builder, Movie movie) {
        builder.addField("__**PLOT:**__", movie.getPlot(), false);
    }

    private void appendInfo(EmbedBuilder builder, Movie movie) {
        builder.addField("", "__**INFO:**__", false);

        builder .addField("runtime", movie.getRuntime(), true)
                .addField("released", movie.getReleaseDate(), true)
                .addField("director", movie.getDirector(), true);
    }

    private void appendRatings(EmbedBuilder builder, Movie movie) {
        builder.addField("", "__**RATINGS:**__", false);

        MovieRating[] ratings = movie.getRatings();
        for(MovieRating rating : ratings) {
            builder.addField(rating.getSource(),rating.getValue(), true);
        }
    }

    private void setPoster(EmbedBuilder builder, Movie movie) {
        if(!movie.getPoster().equals("N/A")) {
            builder.setImage(movie.getPoster());
        }
    }

    private void setFooter(EmbedBuilder builder) {
        builder.setFooter("Movie Bot [oo]<");
    }
}
