package com.pbo.movieBot.bot.commands.schedule.nlp;

import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.pattern.SingleTokenPattern;
import com.pbo.movieBot.nlp.token.DateTimeToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.time.LocalDateTime;
import java.util.List;

public class SchedulingDateTimeReducer implements Reducer<LocalDateTime> {
    @Override
    public Pattern getPattern() {
        return new ListPattern(
                new SingleTokenPattern(new StringToken("for", "")),
                new ClassPattern(DateTimeToken.class)
        );
    }

    @Override
    public Token<LocalDateTime> reduce(List<Token<?>> tokens) {
        String stringPart = tokens.get(0).getStringPart() + tokens.get(1).getStringPart();
        LocalDateTime value = (LocalDateTime) tokens.get(1).getValue();
        return new SchedulingDateTimeToken(value, stringPart);
    }
}
