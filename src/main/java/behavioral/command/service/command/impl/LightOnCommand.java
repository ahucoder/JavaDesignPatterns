package behavioral.command.service.command.impl;

import behavioral.command.service.command.Command;
import behavioral.command.service.device.impl.LightDevice;

public class LightOnCommand implements Command {
    private final LightDevice light;
    private int previousBrightness;

    public LightOnCommand(LightDevice light) {
        this.light = light;
    }

    @Override
    public void execute() {
        previousBrightness = light.getBrightness();
        light.turnOn();
        light.setBrightness(100);
    }

    @Override
    public void undo() {
        light.turnOff();
        light.setBrightness(previousBrightness);
    }

    @Override
    public String description() {
        return "turn on " + light.getName() + " and set brightness to 100%";
    }
}
