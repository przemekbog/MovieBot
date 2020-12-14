package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import net.dv8tion.jda.api.Permission;

import java.util.List;

public class TestCommand extends Command {
    public TestCommand() {
        this.name = "test";
        this.aliases = List.of(
                "t",
                "est"
        );
        this.requiredPermissions.add(Permission.VOICE_USE_VAD);
    }

    @Override
    protected void run(CommandEvent event) {
        event.getChannel().sendMessage("Test").queue();

        RequestCommand requestCommand = new RequestCommand(event);

        requestCommand.setMessage("Give").setOnResponseGot(s ->
                event.getChannel().sendMessage("You typed: \"" + String.join(" ", s) +"\"").queue()
        ).request();
    }
}
