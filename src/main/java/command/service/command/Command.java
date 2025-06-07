package command.service.command;

public interface Command {
    void execute();

    void undo();

    String description();
}
