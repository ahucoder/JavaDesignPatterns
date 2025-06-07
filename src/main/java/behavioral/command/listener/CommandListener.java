package behavioral.command.listener;

import behavioral.command.service.command.Command;

public interface CommandListener {
    void onCommandAction(Command command, boolean executed);
}
