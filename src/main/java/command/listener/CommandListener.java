package command.listener;

import command.service.command.Command;

public interface CommandListener {
    void onCommandAction(Command command, boolean executed);
}
