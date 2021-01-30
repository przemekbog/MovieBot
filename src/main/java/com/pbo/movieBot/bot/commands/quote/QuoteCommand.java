package com.pbo.movieBot.bot.commands.quote;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandHelp;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class QuoteCommand extends Command {
    private static List<String> quotes = getClockworkQuotes();

    @Override
    public String getName() {
        return "quote";
    }

    @Override
    public CommandHelp getHelp() {
        return new QuoteCommandHelp();
    }

    @Override
    public void execute(CommandEvent event, Object context) {
        if(!event.getArgs().isEmpty()) {
            event.getChannel().sendMessage("No arguments are expected").queue();
            return;
        }

        Random randomNumberGenerator = new Random();
        int randomIndex = randomNumberGenerator.nextInt(quotes.size());
        String randomQuote = quotes.get(randomIndex);

        MessageEmbed quoteEmbed = getQuoteEmbed(randomQuote);
        event.getChannel().sendMessage(quoteEmbed).queue();
    }

    private MessageEmbed getQuoteEmbed(String quote) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("Quote")
                .setDescription("\"" + quote + "\"")
                .setColor(Color.ORANGE);

        return builder.build();
    }

    private static List<String> getClockworkQuotes() {
        return List.of(
                "**Alex:** Eggiweggs!",
                "**Alex:** I Was Cured, All Right",
                "**Alex:** What are we gonna do? Talk about me sex life?",
                "**Alex:** I've taught you much, my little droogies.",
                "**Alex:** Hey, dad... there's a strange fella sitting in the sofa. Munchy-wunching lobschticks of toast.",
                "**Alex:** Come and get one in the yarbles! If you have any yarbles that is!",
                "**Alex:** How art thou?! Thou bottle of cheap, stinking chip oil.",
                "**Alex:** Eggy Wegs... I would like... to smash 'em.",
                "**Alex:** It had been a wonderful evening and what i needed now, to give it the perfect ending, was a bit of the old Ludvik van",
                """
                
                **Alex:** No. No! NO! Stop it! Stop it, please! I beg you! This is sin! This is sin! This is sin! It's a sin, it's a sin, it's a sin!
                **Dr. Brodsky:** Sin? What's all this about sin?
                **Alex:** That! Using Ludwig van like that! He did no harm to anyone. Beethoven just wrote music!
                **Dr. Brodsky:** Are you referring to the background score?
                """
        );
    }
}
