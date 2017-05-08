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
        openConnection();
    }

    public void openConnection()  {
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1,0);
            serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
            Thread.sleep(3000);
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("new connection");
    }

    public void closeConnection() {
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void command(String input) {
        try {
            serialPort.writeString(input);
            Thread.sleep(2000);
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void command() {
        command(ob.nextLine());
    }

    public void testCommand() {
        ob = new Scanner(System.in);
        String input = "state";
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
        ob.close();
    }

    public void start(int[] route) {
        openConnection();
        //geef route aan arduino
    }

    public String getState() throws NullPointerException {
        String state = null;
        try {
            serialPort.writeString("state");
            Thread.sleep(2000);
            state = serialPort.readString();
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("command");

        }
        return state;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                String receivedData = serialPort.readString(event.getEventValue());
                System.out.println("Received response from port: " + receivedData);
                Thread.sleep(200);
            }
            catch (SerialPortException ex) {
                System.out.println("Error in receiving response from port: " + ex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}

