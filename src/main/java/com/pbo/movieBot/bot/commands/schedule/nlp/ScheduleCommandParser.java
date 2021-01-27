package com.pbo.movieBot.bot.commands.schedule.nlp;

import com.pbo.movieBot.bot.commands.schedule.nlp.exception.InvalidInputFormatException;
import com.pbo.movieBot.bot.commands.schedule.nlp.exception.InvalidMovieTitleException;
import com.pbo.movieBot.movieApi.MovieFetcher;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.nlp.base.Parser;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.token.DateTimeToken;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleCommandParser implements Parser<MovieReservation> {
    @Override
    public MovieReservation parse(List<Token<?>> tokens) {
        if(!areTokensValid(tokens)) {
            throw new InvalidInputFormatException("The last token must be a DateTimeToken");
        }

        int lastIndex = tokens.size() - 1;
        List<Token<?>> titlePart = tokens.subList(0, lastIndex);
        String title = combineStringParts(titlePart);

        if(!isTitleValid(title)) {
            throw new InvalidMovieTitleException("Movie with given title must exist");
        }

        LocalDateTime dateTime = (LocalDateTime) tokens.get(lastIndex).getValue();
        return new MovieReservation(title, dateTime);
    }

    private boolean areTokensValid(List<Token<?>> tokens) {
        if(tokens.size() <= 1) {
            return false;
        }

        int lastIndex = tokens.size() - 1;
        Token<?> lastToken = tokens.get(lastIndex);

        return lastToken instanceof SchedulingDateTimeToken;
    }

    private boolean isTitleValid(String title) {
        MovieFetcher fetcher = MovieFetcher.withMovieTitle(title);
        Movie movie = fetcher.fetch();
        return !movie.getTitle().equals("N/A");
    }

    private String combineStringParts(List<Token<?>> tokens) {
        StringBuilder builder = new StringBuilder();

        for(Token<?> token : tokens) {
            builder.append(token.getStringPart());
        }

        return builder.toString();
    }
}
