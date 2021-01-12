package com.pbo.movieBot;

import com.pbo.movieBot.movieSaving.base.MovieEntry;
import com.pbo.movieBot.movieSaving.base.MovieSaver;
import com.pbo.movieBot.movieSaving.base.filtering.TitleSpecification;
import com.pbo.movieBot.movieSaving.JsonSaver;

import javax.security.auth.login.LoginException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Bot {
    public static void main(String[] args) throws LoginException {
        MovieSaver saver = new JsonSaver("localData/reservations.json");
        saver.clear();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        saver.add(new MovieEntry("Tarzan", date, time));
        saver.add(new MovieEntry("Tarzan", date, time));
        saver.add(new MovieEntry("Shit", date, time));

        System.out.println(saver.getBySpecification(new TitleSpecification("Tarzan")));

//        CommandExecutorImpl<MovieBotContext> executor = new CommandExecutorImpl<>(new MovieBotContext());
//        executor.addCommands(
//                new HelpCommand(),
//                new TestCommand1(),
//                new TestCommand2(),
//                new TestCommand3(),
//                new TestCommand4()
//        );
//
//        CommandListener listener = new CommandListener(executor, "!");
//
//        JDABuilder.createLight(Configuration.getDiscordAPIKey(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
//                .addEventListeners(listener)
//                .setActivity(Activity.playing("Type !test"))
//                .build();
    }
}
