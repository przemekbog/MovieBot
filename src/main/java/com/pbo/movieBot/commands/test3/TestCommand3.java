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
import com.pbo.movieBot.nlp.listReducer.IterativeListReducer;
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
        pipeline.setListReducers(
                new IterativeListReducer(new IntegerReducer()),
                new IterativeListReducer(new HourMinuteTimeReducer()),
                new IterativeListReducer(new HourAmPmTimeReducer())
        );
        pipeline.setParser(new ParserImpl());

        MessageChannel channel = event.getChannel();
        List<Token<?>> shit = pipeline.process(args);
        channel.sendMessage(shit.toString()).queue();
    }


}
