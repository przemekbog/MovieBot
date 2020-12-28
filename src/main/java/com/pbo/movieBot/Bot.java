package com.pbo.movieBot;

import com.pbo.movieBot.commands.Context;
import com.pbo.movieBot.commands.test1.TestCommand1;
import com.pbo.movieBot.command.CommandExecutorImpl;
import com.pbo.movieBot.command.CommandListener;
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

        CommandExecutorImpl<Context> executor = new CommandExecutorImpl<>(new Context());
        executor.addCommands(
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
