package com.pbo.movieBot.bot.commands.reschedule.nlp;

import com.pbo.movieBot.bot.commands.schedule.nlp.SchedulingDateTimeReducer;
import com.pbo.movieBot.movieReservations.base.MovieReservation;
import com.pbo.movieBot.movieReservations.base.filtering.Specification;
import com.pbo.movieBot.nlp.NLPPipeline;
import com.pbo.movieBot.nlp.base.ListReducer;
import com.pbo.movieBot.nlp.base.Reducer;
import com.pbo.movieBot.nlp.listReducer.IterativeListReducer;
import com.pbo.movieBot.nlp.reducer.*;
import com.pbo.movieBot.nlp.tokenizer.TokenizerImpl;
import net.dv8tion.jda.internal.utils.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class ReschedulingPipeline extends NLPPipeline<Pair<Specification<MovieReservation>, MovieReservation>> {

    public ReschedulingPipeline() {
        super(new TokenizerImpl(), getListReducers(), new ReschedulingParser());
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
                new MonthDayReducer(),
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
