package ordersys.com;

import arduino.Arduino;

import java.util.Scanner;

/**
 * Created by ian on 04/05/2017.
 */
public class Robot {

    Scanner ob;
    Arduino arduino;
    String input;
    boolean started = false, paused = false;

    public Robot() {
        ob = new Scanner(System.in);
        arduino = new Arduino("cu.usbmodem1411", 9600); //enter the port name here, and ensure that Arduin
    }

    public void openConnection() {
        arduino.openConnection();
        System.out.println("Enter 1 to switch LED on and 0  to switch LED off");
        input = ob.nextLine();
        while(input != "n"){
            command(input);
            input = ob.nextLine();
        }
        ob.close();
        arduino.closeConnection();
    }

    public void command(String input) {
        arduino.serialWrite(input);
    }

    public void start(int[] route) {
        started = true;
        // doe iets
    }

    public void pause() {
        paused = !paused;
    }

    public String getStatus() {
        String status = "iets";

        return status;
    }

    public void closeConnection() {
        ob.close();
        arduino.closeConnection();
    }
}
