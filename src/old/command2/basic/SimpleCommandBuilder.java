package com.pbo.movieBot.command2.basic;

import com.pbo.movieBot.command2.basic.base.Command;
import com.pbo.movieBot.command2.basic.base.CommandEvent;

import java.util.function.Consumer;

public class SimpleCommandBuilder {
    private Consumer<? super CommandEvent> onExecute;
    private String name;
    private String[] aliases;
    private CommandInfoImpl info;

    public SimpleCommandBuilder(String name) {
        this.name = name;
    }

    public SimpleCommand build() {
        SimpleCommand command = new SimpleCommand(name, aliases, info);
        command.setOnExecute(onExecute);

        return command;
    }

    public SimpleCommandBuilder setOnExecute(Consumer<? super CommandEvent> onExecute) {
        this.onExecute = onExecute;
        return this;
    }

    public SimpleCommandBuilder setAliases(String[] aliases) {
        for (String alias : aliases) {
            if(alias.contains(" ")) {
                throw new IllegalArgumentException("aliases mustn't contain spaces");
            }
        }

        this.aliases = aliases;
        return this;
    }

    public SimpleCommandBuilder setInfo(CommandInfoImpl info) {
        this.info = info;
        return this;
    }

    private class SimpleCommand extends Command {

        private Consumer<? super CommandEvent> onExecute;

        public SimpleCommand(String name, String[] aliases, CommandInfoImpl info) {
            super(name);
            this.aliases = aliases;
            this.info = info;
        }

        @Override
        public void execute(CommandEvent event) {
            if (onExecute != null) {
                onExecute.accept(event);
            }
        }

        public void setOnExecute(Consumer<? super CommandEvent> onExecute) {
            this.onExecute = onExecute;
        }
    }
}
