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
            transporter.testCommand();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}