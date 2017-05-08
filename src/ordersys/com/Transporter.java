package ordersys.com;

import jssc.SerialPortException;

/**
 * Created by ian on 04/05/2017.
 */
public class Transporter extends Robot {

    public Transporter() throws SerialPortException {
        super("/dev/cu.usbmodem1411");
    }
}

