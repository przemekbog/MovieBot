package com.pbo.movieBot.command2.basic;

import com.pbo.movieBot.command2.basic.base.Command;
import com.pbo.movieBot.command2.basic.base.CommandEvent;
import com.pbo.movieBot.command2.basic.base.CommandManager;
import org.jetbrains.annotations.NotNull;

public class ComplexCommand extends Command {
    private OverridingCommandManager overridingManager;
    private CommandManager overridedManager;

    public ComplexCommand(@NotNull String name, @NotNull CommandManager overridedManager) {
        super(name);
        this.overridingManager = new OverridingCommandManager(overridedManager);
        this.overridedManager = overridedManager;
    }

    @Override
    public final void execute(@NotNull CommandEvent event) {
        onBeforeManagerOverride(event);
        overridedManager.setExternalManager(overridingManager);
        onAfterManagerOverride(event);
    }

    public final void stop(@NotNull CommandEvent event) {
        onBeforeStop(event);
        overridingManager.stopOverriding();
        onAfterStop(event);
    }

    public OverridingCommandManager getOverridingManager() {
        return overridingManager;
    }

    public CommandManager getOverridedManager() {
        return overridedManager;
    }

    public void addCommand(Command command) {
        overridingManager.addCommand(command);
    }

    public void addCommands(Command c1, Command... commands) {
        overridingManager.addCommands(c1, commands);
    }

    protected void onBeforeManagerOverride(@NotNull CommandEvent event) {}
    protected void onAfterManagerOverride(@NotNull CommandEvent event) {}
    protected void onBeforeStop(@NotNull CommandEvent event) {}
    protected void onAfterStop(@NotNull CommandEvent event) {}

}
