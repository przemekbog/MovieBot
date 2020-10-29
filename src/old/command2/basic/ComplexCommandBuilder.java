package com.pbo.movieBot.command2.basic;

import com.pbo.movieBot.command2.basic.base.Command;
import com.pbo.movieBot.command2.basic.base.CommandEvent;
import com.pbo.movieBot.command2.basic.base.CommandInfo;
import com.pbo.movieBot.command2.basic.base.CommandManager;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ComplexCommandBuilder {
    private BuiltComplexCommand command;

    public ComplexCommandBuilder(String name, CommandManager overridedManager) {
        this.command = new BuiltComplexCommand(name, overridedManager);
    }

    public ComplexCommandBuilder addCommand(Command command) {
        this.command.getOverridingManager().addCommand(command);
        return this;
    }

    public ComplexCommandBuilder addCommands(Command c1, Command... commands) {
        this.command.getOverridingManager().addCommands(c1, commands);
        return this;
    }

    public ComplexCommandBuilder setOnBeforeManagerOverride(Consumer<CommandEvent> onBeforeManagerOverride) {
        command.setOnBeforeManagerOverride(onBeforeManagerOverride);
        return this;
    }

    public ComplexCommandBuilder setOnAfterManagerOverride(Consumer<CommandEvent> onAfterManagerOverride) {
        command.setOnAfterManagerOverride(onAfterManagerOverride);
        return this;
    }

    public ComplexCommandBuilder setOnBeforeStop(Consumer<CommandEvent> onBeforeStop) {
        command.setOnBeforeStop(onBeforeStop);
        return this;
    }

    public ComplexCommandBuilder setOnAfterStop(Consumer<CommandEvent> onAfterStop) {
        command.setOnAfterStop(onAfterStop);
        return this;
    }

    public ComplexCommandBuilder setAliases(@NotNull String[] aliases) {
        command.setAliases(aliases);
        return this;
    }

    public ComplexCommandBuilder setInfo(CommandInfo info) {
        command.setInfo(info);
        return this;
    }

    private class BuiltComplexCommand extends ComplexCommand {
        private Consumer<CommandEvent> onBeforeManagerOverride;
        private Consumer<CommandEvent> onAfterManagerOverride;
        private Consumer<CommandEvent> onBeforeStop;
        private Consumer<CommandEvent> onAfterStop;

        public BuiltComplexCommand(String name, CommandManager overridedManager) {
            super(name, overridedManager);
        }

        @Override
        protected void onBeforeManagerOverride(@NotNull CommandEvent event) {
            onBeforeManagerOverride.accept(event);
        }

        public void setOnBeforeManagerOverride(Consumer<CommandEvent> onBeforeManagerOverride) {
            this.onBeforeManagerOverride = onBeforeManagerOverride;
        }

        public void setOnAfterManagerOverride(Consumer<CommandEvent> onAfterManagerOverride) {
            this.onAfterManagerOverride = onAfterManagerOverride;
        }

        public void setOnBeforeStop(Consumer<CommandEvent> onBeforeStop) {
            this.onBeforeStop = onBeforeStop;
        }

        public void setOnAfterStop(Consumer<CommandEvent> onAfterStop) {
            this.onAfterStop = onAfterStop;
        }

        @Override
        protected void onAfterManagerOverride(@NotNull CommandEvent event) {
            onAfterManagerOverride.accept(event);
        }

        @Override
        protected void onBeforeStop(@NotNull CommandEvent event) {
            onBeforeStop.accept(event);
        }

        @Override
        protected void onAfterStop(@NotNull CommandEvent event) {
            onAfterStop.accept(event);
        }

        public void setAliases(@NotNull String[] aliases) {

            for(String alias : aliases) {
                if(alias.contains(" ")) {
                    throw new IllegalArgumentException("Aliases mustn't contain spaces");
                }
            }

            this.aliases = aliases;
        }

        public void setInfo(CommandInfo info) {
            this.info = info;
        }
    }
}
