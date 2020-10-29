package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.CommandManager;
import com.pbo.movieBot.command.base.ModifiableManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BasicCommandManager<T> implements ModifiableManager<T> {

    protected Map<String, Command<T>> commandHashMap = new HashMap<>();
    protected List<Command<T>> commands = new ArrayList<>();
    protected T environmentObject;

    private CommandManager externalCommandManager;

    public BasicCommandManager(T environmentVariables) {
        this.environmentObject = environmentVariables;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String commandWithoutPrefix) {
        if(externalCommandManager != null) {
            externalCommandManager.execute(event, commandWithoutPrefix);
            return;
        }

        String commandName = getCommandName(commandWithoutPrefix);
        String[] args = getArgs(commandWithoutPrefix);

        if(!commandHashMap.containsKey(commandName)) {
            System.out.println("Command not found");
            return;
        }

        Command command = commandHashMap.get(commandName);
        command.execute(new CommandEvent(
                event,
                this,
                environmentObject,
                args
        ));

    }

    public void stopOverriding() {
        externalCommandManager = null;
    }

    @Override
    public void addCommands(Command<T> first, Command<T>... other) {
        addCommand(first);
        for(Command c : other) {
            addCommand(c);
        }
    }

    @Override
    public void addCommand(Command<T> command) {
        if(commandHashMap.containsKey(command.getName())) {
            return;
        }

        commandHashMap.put(command.getName(), command);
        commands.add(command);

        if(command.getAliases().size() > 0) {
            for(String alias : command.getAliases()) {
                commandHashMap.put(alias, command);
            }
        }
    }


    public List<Command<T>> getCommands() {
        return commands;
    }

    @Override
    public void overrideBehaviorTo(@NotNull CommandManager manager) {
        this.externalCommandManager = manager;
    }

    @Override
    public void stopOverride() {
        this.externalCommandManager = null;
    }

    @Override
    public boolean isOverriden() {
        return this.externalCommandManager != null;
    }

    @Override
    public CommandManager getOverridingManager() {
        return this.externalCommandManager;
    }

    private void onCommandNotFound(GuildMessageReceivedEvent event, String commandWithoutPrefix) {
        event.getChannel().sendMessage("Command not found").queue();
    }

    private String getCommandName(String command) {
        String[] commandParts = spiltCommand(command);

        if(commandParts.length == 0) {
            return "";
        }

        return commandParts[0];
    }

    private String[] getArgs(String command) {
        String[] commandParts = spiltCommand(command);

        if(commandParts.length < 2) {
            return new String[] {};
        }

        String[] args = Arrays.copyOfRange(commandParts, 1, commandParts.length);
        return args;
    }

    private String[] spiltCommand(String command) {
        return command.split(" ");
    }
}
