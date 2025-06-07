package command;

import command.service.command.Command;
import command.service.command.impl.DelayedCommand;
import command.service.command.impl.LightOffCommand;
import command.service.command.impl.LightOnCommand;
import command.service.command.impl.MacroCommand;
import command.service.device.SmartDeviceController;
import command.service.device.impl.LightDevice;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // Create smart device controller
        SmartDeviceController controller = new SmartDeviceController();

        // Create device
        LightDevice livingRoomLight = new LightDevice("livingRoomLight");
        LightDevice bedroomLight = new LightDevice("bedroomLight");

        // Register device
        controller.registerDevice(livingRoomLight);
        controller.registerDevice(bedroomLight);

        // Create command
        Command livingRoomOn = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command bedroomOn = new LightOnCommand(bedroomLight);
        Command bedroomOff = new LightOffCommand(bedroomLight);

        // Create macro command
        Command homeArrival = new MacroCommand("Home Mode", Arrays.asList(
                livingRoomOn
        ));

        Command bedtime = new MacroCommand("Sleep Mode", Arrays.asList(
                livingRoomOff,
                bedroomOn
        ));

        // Create delay command
        Command delayedLightOff = new DelayedCommand(bedroomOff, 5000);

        // Assign commands to slots of controller
        controller.assignCommand("A1", livingRoomOn);
        controller.assignCommand("A2", livingRoomOff);
        controller.assignCommand("B1", bedroomOn);
        controller.assignCommand("B2", bedroomOff);
        controller.assignCommand("C1", homeArrival);
        controller.assignCommand("C2", bedtime);
        controller.assignCommand("D1", delayedLightOff);

        // Execute command
        System.out.println("===== Execute Home Mode =====");
        controller.pressButton("C1");

        TimeUnit.SECONDS.sleep(1);
        System.out.println("\n===== Current Status =====");
        System.out.println(controller.getStatusReport());

        TimeUnit.SECONDS.sleep(1);
        System.out.println("===== Execute Sleep Mode =====");
        controller.pressButton("C2");

        TimeUnit.SECONDS.sleep(1);
        System.out.println("\n===== Current Status =====");
        System.out.println(controller.getStatusReport());

        System.out.println("===== Delay By 5 Seconds To Turn Off The Bedroom Light =====");
        controller.pressButton("D1");

        System.out.println("\n===== Execute Undo Operation =====");
        controller.undo(); // Cancel delay command
        controller.undo(); // Cancel sleep mode

        System.out.println("\n===== Execute Redo Operation =====");
        controller.redo(); // Redo sleep mode

        TimeUnit.SECONDS.sleep(6); // Waiting for delayed command execution

        System.out.println("\n===== Final Status =====");
        System.out.println(controller.getStatusReport());

        System.out.println("\n===== Command History =====");
        System.out.println(controller.getCommandHistory());

        controller.shutdown();
    }
}
