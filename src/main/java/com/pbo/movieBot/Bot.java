package com.pbo.movieBot;

import com.pbo.movieBot.command.BasicCommandManager;
import com.pbo.movieBot.command.CommandClient;
import com.pbo.movieBot.command.TestCommand;
import com.pbo.movieBot.command.TestComplex;
import com.pbo.movieBot.options.Configuration;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.List;

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
        BasicCommandManager commandManager = new BasicCommandManager();
        commandManager.addCommands(
               new TestCommand(),
               new TestComplex()
        );

        CommandClient commandClient = new CommandClient(commandManager, "!");

        JDABuilder.createLight(Configuration.getDiscordAPIKey(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(commandClient)
                .setActivity(Activity.playing("Type !fuck"))
                .build();
    }
}
