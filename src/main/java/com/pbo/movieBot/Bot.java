package com.pbo.movieBot;

import com.pbo.movieBot.commands.test1.TestCommand1;
import com.pbo.movieBot.command.CommandExecutorImpl;
import com.pbo.movieBot.command.CommandListener;
import com.pbo.movieBot.commands.test2.TestCommand2;
import com.pbo.movieBot.commands.test3.TestCommand3;
import com.pbo.movieBot.options.Configuration;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) throws LoginException {
//        IntegerToken token1 = new IntegerToken(5);
//        IntegerToken token2 = new IntegerToken(10);
//        TokenTemplate template1 = new TokenTemplate(token1);
//        TokenTemplate template2 = new TokenTemplate(IntegerToken.class);
//
//
//
//        System.out.println(template1.matches(token1));
//        System.out.println(template1.matches(token2));
//        System.out.println("");
//        System.out.println(template2.matches(token1));
//        System.out.println(template2.matches(new StringToken("5")));

//        System.out.println(tokenizer.tokenize("Many things have weird characters, like for example ;'./.,. I like tokenization. 2 + 3 = 5"));
//        BasicCommandManager commandManager = new BasicCommandManager();
//        commandManager.addCommands(
//               new TestCommand(),
//               new TestComplex()
//        );
//
//        CommandClient commandClient = new CommandClient(commandManager, "!");
        //

        CommandExecutorImpl executor = new CommandExecutorImpl(new Object());
        executor.addCommands(
                new TestCommand1(),
                new TestCommand2(),
                new TestCommand3()
        );

        CommandListener listener = new CommandListener(executor, "!");

        JDABuilder.createLight(Configuration.getDiscordAPIKey(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(listener)
                .setActivity(Activity.playing("Type !test"))
                .build();
    }
}
