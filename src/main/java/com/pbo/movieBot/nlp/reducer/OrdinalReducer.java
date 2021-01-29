package com.pbo.movieBot.nlp.reducer;

import com.pbo.movieBot.nlp.token.OrdinalNumber;
import com.pbo.movieBot.nlp.base.Pattern;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.pattern.AndPattern;
import com.pbo.movieBot.nlp.pattern.ClassPattern;
import com.pbo.movieBot.nlp.pattern.ListPattern;
import com.pbo.movieBot.nlp.token.IntegerToken;
import com.pbo.movieBot.nlp.token.OrdinalNumberToken;
import com.pbo.movieBot.nlp.token.StringToken;

import java.util.List;

public class OrdinalReducer implements Reducer<OrdinalNumber> {
    @Override
    public Pattern getPattern() {
        return new AndPattern(
                new ListPattern(
                        new ClassPattern(IntegerToken.class),
                        new ClassPattern(StringToken.class)
                ),
                new Pattern() {
                    @Override
                    public int getTokenCount() {
                        return 2;
                    }

                    @Override
                    public boolean matches(List<Token<?>> tokens) {
                        Integer number = (Integer) tokens.get(0).getValue();
                        String suffix = (String) tokens.get(1).getValue();

                        return suffix.equals(OrdinalNumber.getSuffix(number));
                    }
                }
        );
    }

    @Override
    public Token<OrdinalNumber> reduce(List<Token<?>> tokens) {
        IntegerToken number = (IntegerToken) tokens.get(0);
        StringToken suffix = (StringToken) tokens.get(1);

        String stringPart = number.getStringPart() + suffix.getStringPart();
        return new OrdinalNumberToken(
                new OrdinalNumber(number.getValue()),
                stringPart
        );
    }
}
