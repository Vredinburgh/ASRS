package ordersys.com;

/**
 * Created by ian on 04/05/2017.
 *
 * https://sourceforge.net/projects/javaarduinolibrary/files/
 */

import jssc.SerialPortException;

public class SerialTest {

    public static void main(String[] args) {

        Transporter transporter = null;
        try {
            transporter = new Transporter();

//            transporter.command("state");
//            transporter.command("0");
//            transporter.command("state");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Current state: " + transporter.getState());
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

//        System.out.println("testMain");

        //transporter.command("1");
    }
}