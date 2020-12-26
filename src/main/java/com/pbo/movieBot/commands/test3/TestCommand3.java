package com.pbo.movieBot.commands.test3;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.commands.test3.nlp.parser.ParserImpl;
import com.pbo.movieBot.commands.test3.nlp.reducer.HourAmPmTimeReducer;
import com.pbo.movieBot.commands.test3.nlp.reducer.HourMinuteTimeReducer;
import com.pbo.movieBot.commands.test3.nlp.reducer.IntegerReducer;
import com.pbo.movieBot.commands.test3.nlp.tokenizer.TokenizerImpl;
import com.pbo.movieBot.nlp.NLPPipeline;
import com.pbo.movieBot.nlp.base.Token;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.List;

public class TestCommand3 extends Command {
    @Override
    public String getName() {
        return "test3";
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        String args = event.getArgs();

        NLPPipeline<List<Token<?>>> pipeline = new NLPPipeline<>();
        pipeline.setTokenizer(new TokenizerImpl());
        pipeline.setReducers(
                new IntegerReducer(),
                new HourMinuteTimeReducer(),
                new HourAmPmTimeReducer()
        );
        pipeline.setParser(new ParserImpl());

        MessageChannel channel = event.getChannel();
        channel.sendMessage(pipeline.process(args).toString()).queue();
    }


}