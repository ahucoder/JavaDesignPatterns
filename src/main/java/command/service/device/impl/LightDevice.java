package command.service.device.impl;

import command.service.device.SmartDevice;
import lombok.Getter;

public class LightDevice implements SmartDevice {
    private final String name;
    @Getter
    private boolean on;
    @Getter
    private int brightness = 100;

    public LightDevice(String name) {
        this.name = name;
    }

    public void turnOn() {
        on = true;
        System.out.println(name + ": The light is turned on");
    }

    public void turnOff() {
        on = false;
        System.out.println(name + ": The light is turned off");
    }

    public void setBrightness(int level) {
        brightness = Math.max(0, Math.min(100, level));
        System.out.printf("%s: set brightness to %d%%%n", name, brightness);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStatus() {
        return String.format("%s: %s (brightness: %d%%)",
                name, on ? "on" : "off", brightness);
    }
}
