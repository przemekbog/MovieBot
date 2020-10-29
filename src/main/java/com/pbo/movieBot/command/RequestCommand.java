package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandManager;
import com.pbo.movieBot.command.base.ModifiableManager;

import java.util.concurrent.locks.ReentrantLock;

public class RequestCommand {

    private CommandEvent event;
    private Command responseCommand = new ResponseCommand();
    private ModifiableManager overridingManager = new BasicCommandManager(new Object());
    private CommandManager overridedManager;
    private String response;
    private Object lock = new Object();

    public RequestCommand(CommandEvent event) {
        this.event = event;
        overridingManager.addCommand(responseCommand);
    }

    public synchronized void request(String msg) {
        event.getChannel().sendMessage(msg).queue();
        overridedManager = event.getCommandManager();
        overridedManager.overrideBehaviorTo(overridingManager);
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("printing response");
            event.getChannel().sendMessage(response != null ? response : "mep");
        }
    }

    public String getResponse() {
        return response;
    }

    private class ResponseCommand extends Command {

        public ResponseCommand() {
            this.name = "give";
        }

        @Override
        protected void run(CommandEvent event) {
            event.getChannel().sendMessage("Thx").queue();
            RequestCommand.this.response = String.join(" ", event.getArgs());
            RequestCommand.this.notifyAll();
        }
    }


}
