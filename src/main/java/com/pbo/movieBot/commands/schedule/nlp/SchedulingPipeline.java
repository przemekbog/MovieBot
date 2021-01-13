package com.pbo.movieBot.commands.schedule.nlp;

import com.pbo.movieBot.movieSaving.base.MovieReservation;
import com.pbo.movieBot.nlp.NLPPipeline;
import com.pbo.movieBot.nlp.base.ListReducer;
import com.pbo.movieBot.nlp.base.Parser;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.base.Tokenizer;
import com.pbo.movieBot.nlp.listReducer.IterativeListReducer;
import com.pbo.movieBot.nlp.reducer.*;
import com.pbo.movieBot.nlp.tokenizer.TokenizerImpl;

import java.util.List;
import java.util.stream.Collectors;

public class SchedulingPipeline extends NLPPipeline<MovieReservation> {
    public SchedulingPipeline() {
        super(new TokenizerImpl(), getListReducers(), new ScheduleCommandParser());
    }

    private static List<ListReducer> getListReducers() {
        List<Reducer> reducers = List.of(
                new IntegerReducer(),
                new OrdinalReducer(),

                new HourMinuteTimeReducer(),
                new HourAmPmTimeReducer(),
                new TimeAmPmReducer(),
                new ThisTimeReducer(),
                new AtTimeReducer(),

                new MonthReducer(),
                new DayMonthWordYearReducer(),
                new DayOfMonthReducer(),
                new DayMonthYearReducer(),
                new NextSomethingDateReducer(),
                new TodayReducer(),
                new TomorrowReducer(),
                new OnDateReducer(),

                new TimeDateReducer(),
                new DateTimeReducer()
        );

        return reducers.stream().
                map(reducer -> new IterativeListReducer(reducer))
                .collect(Collectors.toList());
    }
}
