package com.pbo.movieBot.command.base;


import com.pbo.movieBot.command.BasicCommandManager;

/**
 * ComplexCommand is a command, which when executed,
 * overrides the command manager with its own
 * @param <T> type of the environment object
 */
public abstract class ComplexCommand<T> extends Command<T> {

    private BasicCommandManager<T> overridingManager;
    private CommandManager overridedManager;

    public ComplexCommand(T environmentVariables) {
        overridingManager = new BasicCommandManager<>();
        overridingManager.setEnvironmentObject(environmentVariables);
    }

    @Override
    public void execute(CommandEvent<T> event) {
        super.execute(event);
        overridedManager = event.getCommandManager();

        onBeforeOverride(event);
        overridedManager.overrideBehaviorTo(overridingManager);
        onAfterOverride(event);
    }

    public ModifiableManager<T> getOverridingManager() {
        return overridingManager;
    }

    public CommandManager getOverridedManager() {
        return overridedManager;
    }

    public void stop(CommandEvent<T> event) {
        onBeforeStopOverride(event);
        overridedManager.stopOverride();
        onAfterStopOverride(event);
    }

    private void onBeforeOverride(CommandEvent<T> event) {
        /*to be implemented by child classes*/
    }

    private void onAfterOverride(CommandEvent<T> event) {
        /*to be implemented by child classes*/
    }

    private void onBeforeStopOverride(CommandEvent<T> event) {
        /*to be implemented by child classes*/
    }

    private void onAfterStopOverride(CommandEvent<T> event) {
        /*to be implemented by child classes*/
    }

}
