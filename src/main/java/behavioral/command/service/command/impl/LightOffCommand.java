package behavioral.command.service.command.impl;

import behavioral.command.service.command.Command;
import behavioral.command.service.device.impl.LightDevice;

public class LightOffCommand implements Command {
    private final LightDevice light;
    private int previousBrightness;
    private boolean wasOn;

    public LightOffCommand(LightDevice light) {
        this.light = light;
    }

    @Override
    public void execute() {
        wasOn = light.isOn();
        previousBrightness = light.getBrightness();
        light.turnOff();
    }

    @Override
    public void undo() {
        if (wasOn) {
            light.turnOn();
            light.setBrightness(previousBrightness);
        }
    }

    @Override
    public String description() {
        return "turn off " + light.getName();
    }
}
