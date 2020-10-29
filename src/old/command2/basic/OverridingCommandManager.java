package com.pbo.movieBot.command2.basic;

import com.pbo.movieBot.command2.basic.base.CommandManager;

public class OverridingCommandManager extends BasicCommandManager {

    protected CommandManager overridedManager;

    public OverridingCommandManager(CommandManager overridedManager) {
        this.overridedManager = overridedManager;
    }

    public CommandManager getOverridedManager() {
        return overridedManager;
    }

    public void stopOverriding() {
        overridedManager.removeExternalManager();
    }
}
