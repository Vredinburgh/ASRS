package ordersys.com;

/**
 * Created by ian on 04/05/2017.
 *
 * https://sourceforge.net/projects/javaarduinolibrary/files/
 */

import jssc.SerialPortException;

public class SerialTest {

    public static void main(String[] args) {

        for(int i = 0; i < PortFinder.portNames.length; i++) {
            System.out.println(PortFinder.portNames[i]);
        }

        Transporter transporter = null;
        try {
            transporter = new Transporter(PortFinder.portNames[0]);
            transporter.testCommand();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}