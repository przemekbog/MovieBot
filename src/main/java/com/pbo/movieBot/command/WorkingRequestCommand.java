package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.*;

import java.util.function.Consumer;

public class WorkingRequestCommand {

    private String message;
    private Consumer<String> onResponseGot;
    private CommandEvent event;
    private ModifiableManager overridingManager = new BasicCommandManager(new Object());
    private CommandManager overridedManager;

    public WorkingRequestCommand(CommandEvent event) {
        this.event = event;
        overridingManager.addCommand(new GiveCommand());
    }

    public void request() {
        event.getChannel().sendMessage(message).queue();
        overridedManager = event.getCommandManager();
        overridedManager.overrideBehaviorTo(overridingManager);
    }

    public WorkingRequestCommand setMessage(String message) {
        this.message = message;
        return this;
    }

    public WorkingRequestCommand setOnResponseGot(Consumer<String> onResponseGot) {
        this.onResponseGot = onResponseGot;
        return this;
    }

    private class GiveCommand extends Command {
        public GiveCommand() {
            this.name = "respond";
        }

        @Override
        protected void run(CommandEvent event) {
            onResponseGot.accept(String.join(" ", event.getArgs()));
        }
    }
}
