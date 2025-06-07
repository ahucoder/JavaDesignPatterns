package behavioral.command.manager;

import com.google.common.collect.Lists;
import behavioral.command.listener.CommandListener;
import behavioral.command.service.command.Command;
import behavioral.command.service.command.impl.RedoCommand;
import behavioral.command.service.command.impl.UndoCommand;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager {
    private final Deque<Command> history = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();
    private final List<Command> log = Lists.newArrayList();
    private final List<CommandListener> listeners = Lists.newArrayList();

    public void execute(Command command) {
        command.execute();
        history.push(command);
        redoStack.clear(); // Clear the redo stack when executing a new command
        log.add(command);
        notifyListeners(command, true);
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            redoStack.push(command);
            log.add(new UndoCommand(command));
            notifyListeners(command, false);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            history.push(command);
            log.add(new RedoCommand(command));
            notifyListeners(command, true);
        }
    }

    public void addListener(CommandListener listener) {
        listeners.add(listener);
    }

    public String getLog() {
        return log.stream()
                .map(Command::description)
                .collect(Collectors.joining("\n"));
    }

    private void notifyListeners(Command command, boolean executed) {
        listeners.forEach(l -> l.onCommandAction(command, executed));
    }
}
