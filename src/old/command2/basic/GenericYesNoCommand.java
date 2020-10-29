package com.pbo.movieBot.command2.basic;

import com.pbo.movieBot.command2.basic.base.Command;
import com.pbo.movieBot.command2.basic.base.CommandEvent;
import com.pbo.movieBot.command2.basic.base.CommandManager;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GenericYesNoCommand extends ComplexCommand {
    private Consumer<CommandEvent> onYesChosen;
    private Consumer<CommandEvent> onNoChosen;

    public GenericYesNoCommand(@NotNull String name, @NotNull CommandManager overridedManager) {
        super(name, overridedManager);
        this.getOverridingManager().addCommands(new YesCommand(), new NoCommand());
    }

    @Override
    protected void onAfterManagerOverride(@NotNull CommandEvent event) {
        onStart(event);
    }

    protected void onStart(CommandEvent event) { }
    protected void onYesChosen(CommandEvent event) { }
    protected void onNoChosen(CommandEvent event) { }

    private class YesCommand extends Command  {
        public YesCommand() {
            super("yes");
        }

        @Override
        public void execute(@NotNull CommandEvent event) {
            GenericYesNoCommand.this.onYesChosen(event);
            GenericYesNoCommand.this.stop(event);
        }
    }

    private class NoCommand extends Command  {
        public NoCommand() {
            super("no");
        }

        @Override
        public void execute(@NotNull CommandEvent event) {
            GenericYesNoCommand.this.onNoChosen(event);
            GenericYesNoCommand.this.stop(event);
        }
    }
}
