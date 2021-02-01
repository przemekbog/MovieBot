package com.pbo.movieBot.bot.commands.reschedule.nlp;

import com.pbo.movieBot.bot.commands.reschedule.nlp.exception.IncorrectSyntaxException;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.filtering.*;
import com.pbo.movieBot.nlp.base.Parser;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.pattern.OrPattern;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.DateTimeToken;
import com.pbo.movieBot.nlp.token.DateToken;
import com.pbo.movieBot.nlp.token.StringToken;
import net.dv8tion.jda.internal.utils.tuple.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReschedulingParser implements Parser<Pair<Specification<MovieReservation>, MovieReservation>> {
    @Override
    public Pair<Specification<MovieReservation>, MovieReservation> parse(List<Token<?>> tokens) {
        if(!isValid(tokens)) {
            throw new IncorrectSyntaxException();
        }

        LocalDateTime destinationDateTime = (LocalDateTime) tokens.get(tokens.size() - 1).getValue();

        if(!hasFromDateOrDateTime(tokens)) {
            String title = collectStringParts(tokens.subList(0, tokens.size() - 2));
            return Pair.of(
                    new CaseInsensitiveTitleSpecification(title),
                    new MovieReservation(title, destinationDateTime)
            );
        }

        ArrayList<Specification<MovieReservation>> specifications = new ArrayList<>();
        String title = collectStringParts(tokens.subList(0, tokens.size() - 4));
        specifications.add(new CaseInsensitiveTitleSpecification(title));

        Token<?> dateOrDateTimeToken = tokens.get(tokens.size() - 3);
        if(dateOrDateTimeToken instanceof DateTimeToken) {
            LocalDateTime value = (LocalDateTime) dateOrDateTimeToken.getValue();

            specifications.add(new DateSpecification(value.toLocalDate()));
            specifications.add(new TimeSpecification(value.toLocalTime()));
        } else {
            LocalDate date = (LocalDate) dateOrDateTimeToken.getValue();
            specifications.add(new DateSpecification(date));
        }

        return Pair.of(
                new AndSpecification<>(specifications),
                new MovieReservation(title, destinationDateTime)
        );
    }

    boolean isValid(List<Token<?>> tokens) {
        if(tokens.size() < 3) {
            return false;
        }

        List<Token<?>> lastTwo = tokens.subList(tokens.size() - 2, tokens.size());
        Pattern pattern = new ListPattern(
                new SingleTokenPattern(new StringToken("to", "")),
                new ClassPattern(DateTimeToken.class)
        );

        return pattern.matches(lastTwo);
    }

    boolean hasFromDateOrDateTime(List<Token<?>> tokens) {
        if (tokens.size() < 5) {
            return false;
        }

        List<Token<?>> possibleTokens = tokens.subList(tokens.size() - 4, tokens.size() - 2);
        Pattern pattern = new ListPattern(
                new SingleTokenPattern(new StringToken("from", "")),
                new OrPattern(
                        new ClassPattern(DateToken.class),
                        new ClassPattern(DateTimeToken.class)
                )
        );

        return pattern.matches(possibleTokens);
    }

    String collectStringParts(List<Token<?>> tokens) {
        StringBuilder builder = new StringBuilder();

        for(Token<?> token : tokens) {
            builder.append(token.getStringPart());
        }

        return builder.toString().trim();
    }



}
