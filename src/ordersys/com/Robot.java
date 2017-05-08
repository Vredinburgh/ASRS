package ordersys.com;

import jssc.*;

import java.util.Scanner;


/**
 * Created by ian on 04/05/2017.
 *
 * https://code.google.com/archive/p/java-simple-serial-connector/
 */
public class Robot implements SerialPortEventListener {

    Scanner ob;
    SerialPort serialPort;

    public Robot(String portDescription) throws SerialPortException {
        serialPort = new SerialPort(portDescription);

//        serialPort.openPort();
//        serialPort.setParams(9600, 8, 1,0);
//        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
//                SerialPort.FLOWCONTROL_RTSCTS_OUT);

    }

    public void openConnection() {
        ob = new Scanner(System.in);
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1,0);
            serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        System.out.println("new connection");
    }

    public void closeConnection() {
        ob.close();
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void command(String input) {
        openConnection();
        try {
            serialPort.writeString(input);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void command() {
        command(ob.nextLine());
    }

    public void testCommand() {
        openConnection();
        String input = ob.nextLine();
        while(input != "n") {
            try {
                serialPort.writeString(input);
            } catch (SerialPortException e) {
                e.printStackTrace();
            } finally {
                System.out.println("command");
                input = ob.nextLine();
            }
        }
        closeConnection();
    }

    public void start(int[] route) {
        openConnection();
        //geef route aan arduino
    }

    public String getState() throws NullPointerException {
        String state = null;
        openConnection();
        try {
            serialPort.writeString("state");
            //state = serialPort.readString();
        } catch (SerialPortException e) {
            e.printStackTrace();
        } finally {
            System.out.println("command");
            closeConnection();
        }
        return state;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                String receivedData = serialPort.readString(event.getEventValue());
                System.out.println("Received response from port: " + receivedData);
            }
            catch (SerialPortException ex) {
                System.out.println("Error in receiving response from port: " + ex);
            }
        }
    }


}

