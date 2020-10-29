package com.pbo.movieBot.command;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandEvent;
import com.pbo.movieBot.command.base.ComplexCommand;

public class TestComplex extends ComplexCommand {

    public TestComplex() {
        super(new Object());
        this.name = "complex";
        getOverridingManager().addCommands(
                new Command1(),
                new Command2(),
                new Command3(),
                new ExitCommand()
        );
    }

    @Override
    protected void run(CommandEvent event) {
        event.getChannel().sendMessage("Command started").queue();
    }

    private class Command1 extends Command {
        public Command1() {
            this.name = "1";
        }

        @Override
        protected void run(CommandEvent event) {
            event.getChannel().sendMessage("1 run").queue();
        }
    }


    private class Command2 extends Command {
        public Command2() {
            this.name = "2";
        }

        @Override
        protected void run(CommandEvent event) {
            event.getChannel().sendMessage("2 run").queue();
        }
    }


    private class Command3 extends Command {
        public Command3() {
            this.name = "3";
        }

        @Override
        protected void run(CommandEvent event) {
            event.getChannel().sendMessage("3 run").queue();
        }
    }

    private class ExitCommand extends Command {
        public ExitCommand() {
            this.name = "exit";
        }

        @Override
        protected void run(CommandEvent event) {
            event.getChannel().sendMessage("exited").queue();
            TestComplex.this.stop(event);
        }
    }

}
