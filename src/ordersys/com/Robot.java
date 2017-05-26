package ordersys.com;

import jssc.*;

/**
 * Created by ian on 04/05/2017.
 *
 * https://code.google.com/archive/p/java-simple-serial-connector/
 */
public class Robot {

    private Controller controller;

    public Robot(String portDescription) throws SerialPortException {
        controller = new Controller(portDescription);
    }

    public void command(String input) {
        controller.sendCommand(input);
    }

    public void testCommand() {
        controller.startTestCommand();
    }

    public void sendRoute(int[][] route) {
        controller.sendRoute(route);
    }
    
    public String getMessage() {
        return controller.getMessage();
    }
    
    public void setMessage() {
        controller.setMessage();
    }
 }

