package com.pbo.movieBot.commands.schedule.nlp;

import com.pbo.movieBot.commands.schedule.nlp.exception.InvalidInputFormatException;
import com.pbo.movieBot.commands.schedule.nlp.exception.InvalidMovieTitleException;
import com.pbo.movieBot.movieApi.MovieFetcher;
import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieSaving.base.MovieReservation;
import com.pbo.movieBot.nlp.base.Parser;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.token.DateTimeToken;

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

        DateTimeToken timeToken = (DateTimeToken) tokens.get(lastIndex);
        return new MovieReservation(title, timeToken.getValue());
    }

    private boolean areTokensValid(List<Token<?>> tokens) {
        int lastIndex = tokens.size() - 1;
        Token<?> lastToken = tokens.get(lastIndex);

        return lastToken instanceof DateTimeToken;
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
