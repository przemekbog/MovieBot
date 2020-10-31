package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class RequestCommand {

    private String message;
    private Consumer<List<String>> onResponseGot;
    private CommandEvent event;
    private ModifiableManager overridingManager = new BasicCommandManager();
    private CommandManager overridedManager;

    public RequestCommand(CommandEvent event) {
        this.event = event;
        overridingManager.addCommand(new RespondCommand());
    }

    public void request() {
        event.getChannel().sendMessage(message).queue();
        overridedManager = event.getCommandManager();
        overridedManager.overrideBehaviorTo(overridingManager);
    }

    public RequestCommand setMessage(String message) {
        this.message = message;
        return this;
    }

    public RequestCommand setOnResponseGot(Consumer<List<String>> onResponseGot) {
        this.onResponseGot = onResponseGot;
        return this;
    }

    private class RespondCommand extends Command {
        public RespondCommand() {
            this.name = "respond";
        }

        @Override
        protected void run(CommandEvent event) {
            onResponseGot.accept(Arrays.asList(event.getArgs()));
        }
    }
}
