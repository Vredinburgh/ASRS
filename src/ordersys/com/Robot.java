package ordersys.com;

import arduino.Arduino;

import java.util.Scanner;

/**
 * Created by ian on 04/05/2017.
 */
public class Robot {

    Scanner ob;
    Arduino arduino;

    public Robot(String portDescription) {
        ob = new Scanner(System.in);
        arduino = new Arduino(portDescription, 9600);
    }

    public void openConnection() {
        arduino.openConnection();
        System.out.println("test");

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

    public void start(int[] route) {
        openConnection();
        //geef route aan arduino
    }

    public String getState() {
        openConnection();
        arduino.serialWrite("state");
        String state = "iets";
        closeConnection();
        return state;
    }
}
