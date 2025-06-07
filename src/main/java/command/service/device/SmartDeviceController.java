package command.service.device;

import com.google.common.collect.Maps;
import command.manager.CommandManager;
import command.service.command.Command;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartDeviceController {
    private final CommandManager manager = new CommandManager();
    private final Map<String, SmartDevice> devices = Maps.newHashMap();
    private final Map<String, Command> commandSlots = Maps.newHashMap();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    public SmartDeviceController() {
        // add history command listener
        manager.addListener((command, executed) -> {
            String action = executed ? "EXECUTE" : "CANCEL";
            System.out.printf("[HISTORY] %s: %s%n", action, command.description());
        });
    }

    public void registerDevice(SmartDevice device) {
        devices.put(device.getName(), device);
    }

    public void assignCommand(String slotName, Command command) {
        commandSlots.put(slotName, command);
    }

    public void pressButton(String slotName) {
        Command command = commandSlots.get(slotName);
        if (command != null) {
            manager.execute(command);
        }
    }

    public void undo() {
        manager.undo();
    }

    public void redo() {
        manager.redo();
    }

    public void scheduleCommand(Command command, long delay, TimeUnit unit) {
        scheduler.schedule(() -> manager.execute(command), delay, unit);
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    public String getStatusReport() {
        StringBuilder sb = new StringBuilder("=== Smart Device Status Report ===\n");
        sb.append("Time: ").append(LocalDateTime.now()).append("\n\n");

        devices.values().forEach(device -> {
            sb.append(device.getStatus()).append("\n");
        });

        return sb.toString();
    }

    public String getCommandHistory() {
        return manager.getLog();
    }
}
