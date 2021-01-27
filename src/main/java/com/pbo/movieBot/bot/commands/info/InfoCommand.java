package com.pbo.movieBot.bot.commands.info;

import com.pbo.movieBot.bot.utils.MovieInfoEmbedFactory;
import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandHelp;
import com.pbo.movieBot.bot.utils.Emoji;
import com.pbo.movieBot.movieApi.MovieFetcher;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieApi.movie.PlotLength;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class InfoCommand extends Command {
    private static final Color INFO_COLOR = new Color(0xFFC6C6C6, true);
    private MovieInfoEmbedFactory embedFactory = new MovieInfoEmbedFactory();

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
        Movie movie = fetcher.setPlotLength(PlotLength.FULL).fetch();

        MessageChannel channel = event.getChannel();
        if(movie.getTitle().equals("N/A")) {
            sendMovieNotFoundMessage(channel);
            return;
        }

        MessageEmbed infoMessage = embedFactory.getInfo(movie, INFO_COLOR);
        channel.sendMessage(infoMessage).queue();
    }

    private void sendMovieNotFoundMessage(MessageChannel channel) {
        channel.sendMessage("Movie not found " + Emoji.DISAPPOINTED_FACE).queue();
    }


}
