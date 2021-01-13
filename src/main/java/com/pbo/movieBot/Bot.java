package com.pbo.movieBot;

import com.pbo.movieBot.command.CommandExecutorImpl;
import com.pbo.movieBot.command.CommandListener;
import com.pbo.movieBot.commands.MovieBotContext;
import com.pbo.movieBot.commands.help.HelpCommand;
import com.pbo.movieBot.commands.info.InfoCommand;
import com.pbo.movieBot.commands.schedule.ScheduleCommand;
import com.pbo.movieBot.commands.test1.TestCommand1;
import com.pbo.movieBot.commands.test2.TestCommand2;
import com.pbo.movieBot.commands.test3.TestCommand3;
import com.pbo.movieBot.commands.test4.TestCommand4;
import com.pbo.movieBot.options.Configuration;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) throws LoginException {
//        MovieSaver saver = new JsonSaver("localData/reservations.json");
//        saver.clear();
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now();
//        saver.add(new MovieEntry("Tarzan", date, time));
//        saver.add(new MovieEntry("Tarzan", date, time));
//        saver.add(new MovieEntry("Shit", date, time));
//
//        System.out.println(saver.getBySpecification(new TitleSpecification("Tarzan")));

        CommandExecutorImpl<MovieBotContext> executor = new CommandExecutorImpl<>(new MovieBotContext("localData/reservations.json"));
        executor.addCommands(
                new HelpCommand(),
                new InfoCommand(),
                new ScheduleCommand(),
                new TestCommand1(),
                new TestCommand2(),
                new TestCommand3(),
                new TestCommand4()
        );

        CommandListener listener = new CommandListener(executor, "!");

        JDABuilder.createLight(Configuration.getDiscordAPIKey(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(listener)
                .setActivity(Activity.playing("Type !test"))
                .build();
    }
}
