package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.ComplexCommand;
import com.sun.tools.javac.util.List;

public class TestCommand extends Command {
    public TestCommand() {
        this.name = "test";
        this.aliases = List.of(
                "t",
                "est"
        );
    }

    @Override
    protected void run(CommandEvent event) {
        event.getChannel().sendMessage("Test").queue();

        WorkingRequestCommand requestCommand = new WorkingRequestCommand(event);
        requestCommand.setMessage("Give").setOnResponseGot(s -> {
            event.getChannel().sendMessage("You typed: \"" + s +"\"").queue();
        }).request();
    }
}
