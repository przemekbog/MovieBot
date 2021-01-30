package com.pbo.movieBot.bot.commands.announcementChannel;

import com.pbo.movieBot.bot.context.MovieBotContext;
import com.pbo.movieBot.bot.options.Configuration;
import com.pbo.movieBot.bot.utils.Emoji;
import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandHelp;
import net.dv8tion.jda.api.entities.TextChannel;
import org.intellij.lang.annotations.RegExp;

import java.util.List;

public class AnnouncementChannelCommand extends Command<MovieBotContext> {
    @RegExp
    static final String REGEX_MATCHING_CHANNEL_MENTION = "^\\<\\#\\d+\\>$";

    @Override
    public String getName() {
        return "announcement_channel";
    }

    @Override
    public CommandHelp getHelp() {
        return new AnnouncementChannelCommandHelp();
    }

    @Override
    public void execute(CommandEvent event, MovieBotContext context) {
        if(!isArgumentValid(event.getArgs())) {
            sendInvalidSyntax(event);
            return;
        }

        List<TextChannel> channels = event.getMessage().getMentionedChannels();
        TextChannel first = channels.get(0);

        long id = first.getIdLong();

        Configuration.setDefaultChannelId(id);
        Configuration.save();

        sendConfirmation(event);
    }

    private boolean isArgumentValid(String argument) {
        String trimmed = argument.trim();
        return trimmed.matches(REGEX_MATCHING_CHANNEL_MENTION);
    }

    private void sendConfirmation(CommandEvent event) {
        event.getMessage().addReaction(Emoji.OK_HAND).queue();
    }

    private void sendInvalidSyntax(CommandEvent event) {
        event.getMessage().addReaction(Emoji.RAGE).queue();
        event.getChannel().sendMessage("Invalid syntax").queue();
    }
}
