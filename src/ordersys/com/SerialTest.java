package ordersys.com;

/**
 * Created by ian on 04/05/2017.
 *
 * https://sourceforge.net/projects/javaarduinolibrary/files/
 */
import java.util.Scanner;

import arduino.*;

public class SerialTest {

    public static void main(String[] args) {

        Transporter transporter = new Transporter();
        System.out.println("Current state: " + transporter.getState());
        //transporter.testCommand();
    }
}