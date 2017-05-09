package ordersys.com;

import jssc.SerialPortException;

/**
 * Created by ian on 04/05/2017.
 */
public class Transporter extends Robot {

    public Transporter(String portDescription) throws SerialPortException {
        super(portDescription);
    }


}

