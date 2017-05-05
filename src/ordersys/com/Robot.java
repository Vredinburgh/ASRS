package ordersys.com;

import arduino.Arduino;

import java.util.Scanner;

/**
 * Created by ian on 04/05/2017.
 *
 * https://sourceforge.net/projects/javaarduinolibrary/files/
 */
public class Robot {

    Scanner ob;
    Arduino arduino;

    public Robot(String portDescription) {
        arduino = new Arduino(portDescription, 9600);
    }

    public void openConnection() {
        ob = new Scanner(System.in);
        arduino.openConnection();
        System.out.println("new connection");

//        while(input != "n"){
//            if(input == "pause") {
//                paused = !paused;
//            } else if(started && !paused) {
//                command(input);
//                input = ob.nextLine();
//            }
//        }
    }

    public void closeConnection() {
        ob.close();
        arduino.closeConnection();
    }

    public void command(String input) {
        openConnection();
        arduino.serialWrite(input);
        closeConnection();
    }

    public void command() {
        command(ob.nextLine());
    }

    public void testCommand() {
        openConnection();
        String input = ob.nextLine();
        while(input != "n") {
            arduino.serialWrite(input);
            input = ob.nextLine();
            System.out.println("command");
        }
        closeConnection();
    }

    public void start(int[] route) {
        openConnection();
        //geef route aan arduino
    }

    public String getState() {
        arduino.openConnection();
        System.out.println("test");
        arduino.serialWrite("state", 5, 0);
        String state = arduino.serialRead(0);
        System.out.println("Statekk: " + state + " k");
        arduino.closeConnection();
        return state;
    }
}
