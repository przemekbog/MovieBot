package com.pbo.movieBot.bot;

import com.pbo.movieBot.bot.commands.announcementChannel.AnnouncementChannelCommand;
import com.pbo.movieBot.bot.commands.cancel.CancelCommand;
import com.pbo.movieBot.bot.commands.quote.QuoteCommand;
import com.pbo.movieBot.bot.commands.reschedule.RescheduleCommand;
import com.pbo.movieBot.bot.commands.reservations.ReservationsCommand;
import com.pbo.movieBot.bot.commands.smile.SmileCommand;
import com.pbo.movieBot.command.CommandExecutorImpl;
import com.pbo.movieBot.command.CommandListener;
import com.pbo.movieBot.bot.context.MovieBotContext;
import com.pbo.movieBot.bot.commands.help.HelpCommand;
import com.pbo.movieBot.bot.commands.info.InfoCommand;
import com.pbo.movieBot.bot.commands.schedule.ScheduleCommand;
import com.pbo.movieBot.bot.commands.test1.TestCommand1;
import com.pbo.movieBot.bot.commands.test2.TestCommand2;
import com.pbo.movieBot.bot.commands.test3.TestCommand3;
import com.pbo.movieBot.bot.commands.test4.TestCommand4;
import com.pbo.movieBot.bot.options.Configuration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;

public class Bot {
    private static JDA jdaInstance;
    private static Log log = LogFactory.getLog(Bot.class);

    public static JDA getJdaInstance() {
        return jdaInstance;
    }

    public static void main(String[] args) throws LoginException {
        MovieBotContext context = new MovieBotContext("localData/reservations.json");
        CommandExecutorImpl<MovieBotContext> executor = new CommandExecutorImpl<>(context);

        executor.addCommands(
                new HelpCommand(),
                new InfoCommand(),
                new CancelCommand(),
                new ScheduleCommand(),
                new RescheduleCommand(),
                new ReservationsCommand(),
                new AnnouncementChannelCommand(),
                new SmileCommand(),
                new QuoteCommand(),
                new TestCommand1(),
                new TestCommand2(),
                new TestCommand3(),
                new TestCommand4()
        );


        CommandListener listener = new CommandListener(executor, "!");

        log.debug("Starting bot on " + LocalDateTime.now() + "...");
        jdaInstance = JDABuilder.createLight(Configuration.getDiscordAPIKey(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(listener)
                .setActivity(Activity.playing(Configuration.getStatusMessage()))
                .build();
    }
}
