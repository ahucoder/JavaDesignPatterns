package command.service.command.impl;

import command.service.command.Command;

public class RedoCommand implements Command {
    private final Command command;

    public RedoCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public String description() {
        return "[REDO]" + command.description();
    }
}
