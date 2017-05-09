package ordersys.com;

import jssc.SerialPortException;

/**
 * Created by ian on 04/05/2017.
 */
public class Sorter extends Robot {

    public Sorter(String portDescription) throws SerialPortException {
        super(portDescription);
    }
}
