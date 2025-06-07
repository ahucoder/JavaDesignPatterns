package behavioral.command.service.command.impl;

import behavioral.command.service.command.Command;

public class UndoCommand implements Command {
    private final Command command;

    public UndoCommand(Command command) {
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
        return "[UNDO]" + command.description();
    }
}
