package command.service.command.impl;

import command.service.command.Command;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DelayedCommand implements Command {
    private final Command command;
    private final long delayMillis;
    private ScheduledFuture<?> future;
    private final ScheduledExecutorService scheduler;

    public DelayedCommand(Command command, long delayMillis) {
        this.command = command;
        this.delayMillis = delayMillis;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void execute() {
        future = scheduler.schedule(() -> {
            System.out.println("[DELAY EXECUTE] " + command.description());
            command.execute();
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void undo() {
        if (future != null && !future.isDone()) {
            future.cancel(true);
            System.out.println("[CANCEL DELAY EXECUTE COMMAND] " + command.description());
        } else {
            command.undo();
        }
        scheduler.shutdown();
    }

    @Override
    public String description() {
        return String.format("Delay execute after %.1fseconds: %s", delayMillis / 1000.0, command.description());
    }
}
