package ordersys.com;

import java.util.Scanner;

import arduino.*;

/**
 * Created by ian on 04/05/2017.
 */
public class Transporter extends Robot {

    private Scanner ob;
    private Arduino arduino;
    private String input;
    private boolean started = false, paused = false;

    public Transporter() {
        super();
    }

//    @Override
//    public void openConnection() {
//        arduino.openConnection();
//        System.out.println("Enter 1 to switch LED on and 0  to switch LED off");
//        input = ob.nextLine();
//        while(input != "n"){
//            command(input);
//            input = ob.nextLine();
//        }
//        ob.close();
//        arduino.closeConnection();
//    }
//
//    @Override
//    public void command(String input) {
//        arduino.serialWrite(input);
//    }
//
//    @Override
//    public void start(int[] route) {
//        started = true;
//        // doe iets
//    }
//
//    @Override
//    public void pause() {
//        paused = !paused;
//    }
//
//    @Override
//    public String getStatus() {
//        String status = "iets";
//
//        return status;
//    }
//
//    @Override
//    public void closeConnection() {
//        ob.close();
//        arduino.closeConnection();
//    }

}

