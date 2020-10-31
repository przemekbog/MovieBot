package com.pbo.movieBot;



import com.pbo.movieBot.command.*;
import com.pbo.movieBot.key.APIKeys;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) throws LoginException {

        BasicCommandManager commandManager = new BasicCommandManager();
        commandManager.addCommands(
                new TestCommand(),
                new TestComplex()
        );

        CommandClient commandClient = new CommandClient(commandManager, "!");

        JDABuilder.createLight(APIKeys.getDiscordAPIKey(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(commandClient)
                .setActivity(Activity.playing("Type !fuck"))
                .build();
    }
}
