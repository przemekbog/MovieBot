package com.pbo.movieBot.command2.basic;

import com.pbo.movieBot.command2.basic.base.Command;
import com.pbo.movieBot.command2.basic.base.CommandEvent;
import com.pbo.movieBot.command2.basic.base.CommandManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.*;

public class BasicCommandManager implements CommandManager {

    protected Set<Command> commands = new HashSet<>();
    protected Map<String, Command> commandMap = new HashMap<>();
    protected CommandManager externalManager;

    public void addCommand(Command command) {
        if(command.getName().contains(" ")) {
            throw new IllegalArgumentException("Command cannot contain spaces");
        }

        if(commandMap.containsKey(command.getName())) {
            return;
        }

        commands.add(command);
        commandMap.put(command.getName(), command);
        for (String alias : command.getAliases()) {
            commandMap.put(alias, command);
        }
    }

    public void addCommands(Command c1, Command... commands) {
        addCommand(c1);

        for(Command c : commands) {
            addCommand(c);
        }
    }

    @Override
    public void processCommand(GuildMessageReceivedEvent event, String commandWithoutPrefix) {
        if(externalManager != null) {
            externalManager.processCommand(event, commandWithoutPrefix);
            return;
        }

        String commandName = getCommandName(commandWithoutPrefix);
        String args = getArgs(commandWithoutPrefix);

        if(!commandMap.containsKey(commandName)) {
            onCommandNotFound(event);
            return;
        }

        Command command = commandMap.get(commandName);
        command.execute(new CommandEvent(
                event,
                this,
                args
        ));

    }

    public Command[] getCommands() {
        Command[] array = new Command[commands.size()];
        commands.toArray(array);
        return array;
    }

    public CommandManager getExternalManager() {
        return externalManager;
    }

    @Override
    public void setExternalManager(CommandManager externalManager) {
        this.externalManager = externalManager;
    }

    @Override
    public void removeExternalManager() {
        this.externalManager = null;
    }

    protected void onCommandNotFound(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Command not found").queue();
    }

    private String getCommandName(String command) {
        String[] commandParts = spiltCommand(command);

        if(commandParts.length == 0) {
            return "";
        }

        return commandParts[0];
    }

    private String getArgs(String command) {
        String[] commandParts = spiltCommand(command);

        if(commandParts.length < 2) {
            return "";
        }

        String[] agrumentParts = Arrays.copyOfRange(commandParts, 1, commandParts.length);
        return String.join(" ", agrumentParts);
    }

    private String[] spiltCommand(String command) {
        return command.split(" ");
    }

}
