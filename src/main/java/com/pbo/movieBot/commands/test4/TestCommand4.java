package com.pbo.movieBot.commands.test4;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.commands.test3.nlp.parser.ParserImpl;
import com.pbo.movieBot.nlp.NLPPipeline;
import com.pbo.movieBot.nlp.base.ListReducer;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Token;
import com.pbo.movieBot.nlp.listReducer.IterativeListReducer;
import com.pbo.movieBot.nlp.reducer.*;
import com.pbo.movieBot.nlp.tokenizer.TokenizerImpl;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.List;
import java.util.stream.Collectors;

public class TestCommand4 extends Command {
    @Override
    public String getName() {
        return "test4";
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        MessageChannel channel = event.getChannel();
        String args = event.getArgs();

        NLPPipeline<List<Token<?>>> pipeline = getNLPPipeline();
        List<Token<?>> tokens = pipeline.process(args);
        channel.sendMessage(tokens.toString()).queue();
    }

    private NLPPipeline<List<Token<?>>> getNLPPipeline() {
        NLPPipeline<List<Token<?>>> pipeline = new NLPPipeline<>();

        pipeline.setTokenizer(new TokenizerImpl());
        pipeline.setListReducers(getListReducers());
        pipeline.setParser(new ParserImpl());

        return pipeline;
    }

    private List<ListReducer> getListReducers() {
        List<Reducer> reducers = List.of(
                new IntegerReducer(),
                new HourMinuteTimeReducer(),
                new HourAmPmTimeReducer(),
                new TimeAmPmReducer(),
                new HourMinuteTimeReducer(),
                new DayMonthYearReducer(),
                new MonthReducer(),
                new NextSomethingDateReducer(),
                new TodayReducer(),
                new TomorrowReducer(),
                new AtTimeReducer(),
                new OnDateReducer(),
                new TimeDateReducer(),
                new DateTimeReducer()
        );

        return reducers.stream().
                map(reducer -> new IterativeListReducer(reducer))
                .collect(Collectors.toList());
    }
}
