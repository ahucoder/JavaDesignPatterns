package behavioral.command.service.command.impl;

import com.google.common.collect.Lists;
import behavioral.command.service.command.Command;

import java.util.List;
import java.util.ListIterator;

public class MacroCommand implements Command {
    private final String name;
    private final List<Command> commands;

    public MacroCommand(String name, List<Command> commands) {
        this.name = name;
        this.commands = Lists.newArrayList(commands);
    }

    @Override
    public void execute() {
        System.out.println("===== Execution Scene: " + name + " =====");
        commands.forEach(Command::execute);
    }

    @Override
    public void undo() {
        System.out.println("===== Cancel Scene: " + name + " =====");
        // Reverse execution revocation
        ListIterator<Command> iterator = commands.listIterator(commands.size());
        while (iterator.hasPrevious()) {
            iterator.previous().undo();
        }
    }

    @Override
    public String description() {
        return "Scene Mode: " + name + " (" + commands.size() + " Commands)";
    }
}
