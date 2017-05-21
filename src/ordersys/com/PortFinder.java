package ordersys.com;

import jssc.SerialPortList;

/**
 * Created by ian on 09/05/2017.
 */
public class PortFinder {
    public static String[] portNames = SerialPortList.getPortNames();
}

