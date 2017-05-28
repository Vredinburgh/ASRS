package ordersys.com;

import jssc.*;

import java.util.Scanner;

/**
 * Created by ian on 09/05/2017.
 */
public class Controller implements SerialPortEventListener {

    private Scanner ob;
    private SerialPort serialPort;
    
    private String message;

    public Controller(String portDescription) throws SerialPortException {
        serialPort = new SerialPort(portDescription);
        openConnection();
    }

    //Function to open the connection with a COM port
    public void openConnection()  {
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1,0);
            serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
            Thread.sleep(1000);
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("new connection");
    }

    //Function to close the connection with a COM port
    public void closeConnection() {
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    //Function to send a command to the COM port
    public void sendCommand(String input) {
        try {
            Thread.sleep(1000);
            serialPort.writeString(input);
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void startTestCommand() {
        ob = new Scanner(System.in);
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
        ob.close();
    }
    
    public void setMessage() {
        message = null;
    }
    
    public String getMessage() {
        return message;
    }

    //Function to receive data from the COM port
    @Override
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                String receivedData = serialPort.readString(event.getEventValue());
                message = receivedData;
                Thread.sleep(50);
                message = null;
            }
            catch (SerialPortException ex) {
                System.out.println("Error in receiving response from port: " + ex);
            } catch(InterruptedException ex) {
                System.out.println("Error: "+ex);
            }
        }
    }
}
