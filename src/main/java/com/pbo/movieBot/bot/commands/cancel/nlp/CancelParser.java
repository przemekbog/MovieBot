package com.pbo.movieBot.bot.commands.cancel.nlp;

import com.pbo.movieBot.movieApi.movie.Movie;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.filtering.*;
import com.pbo.movieBot.nlp.base.Parser;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.token.DateTimeToken;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.TimeToken;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CancelParser implements Parser<Specification<MovieReservation>> {
    @Override
    public Specification<MovieReservation> parse(List<Token<?>> tokens) {
        Token<?> last = tokens.get(tokens.size() - 1);

        if(last instanceof DateToken || last instanceof TimeToken || last instanceof DateTimeToken) {
            return parseWithDateAndOrTime(tokens);
        }

        String title = collectStringParts(tokens);
        return new TitleSpecification(title);
    }

    private Specification<MovieReservation> parseWithDateAndOrTime(List<Token<?>> tokens) {
        Token<?> last = tokens.get(tokens.size() - 1);
        ArrayList<Specification<MovieReservation>> specifications = new ArrayList<>();

        if(last instanceof DateToken) {
            LocalDate date = (LocalDate) last.getValue();
            specifications.add(new DateSpecification(date));
        }

        if(last instanceof TimeToken) {
            LocalTime time = (LocalTime) last.getValue();
            specifications.add(new TimeSpecification(time));
        }

        if(last instanceof DateTimeToken) {
            LocalDateTime dateTime = (LocalDateTime) last.getValue();
            specifications.add(new DateSpecification(dateTime.toLocalDate()));
            specifications.add(new TimeSpecification(dateTime.toLocalTime()));
        }

        String title = collectStringParts(tokens.subList(0, tokens.size() - 1));
        specifications.add(new TitleSpecification(title));

        return new AndSpecification<>(specifications);
    }

    private String collectStringParts(List<Token<?>> tokens) {
        StringBuilder builder = new StringBuilder();

        for(Token<?> token : tokens) {
            builder.append(token.getStringPart());
        }

        return builder.toString().trim();
    }
}
