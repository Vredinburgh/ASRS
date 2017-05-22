package ordersys.com;

import jssc.*;

import java.util.Scanner;

/**
 * Created by ian on 09/05/2017.
 */
public class Controller implements SerialPortEventListener {

    private Scanner ob;
    private SerialPort serialPort;
    private InstructionsGenerator instructionsGenerator;
    
    private String message;

    public Controller(String portDescription) throws SerialPortException {
        instructionsGenerator = new InstructionsGenerator();
        serialPort = new SerialPort(portDescription);
        openConnection();
    }

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

    public void closeConnection() {
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

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
    
    public String getMessage() {
        return message;
    }

    public void sendRoute(int[][] route) {
        instructionsGenerator.newInstructions(route);
    }

//    public String getState() throws NullPointerException {
//        String state = null;
//        try {
//            serialPort.writeString("state");
//            Thread.sleep(2000);
//            state = serialPort.readString();
//        } catch (SerialPortException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("command");
//
//        }
//        return state;
//    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                String receivedData = serialPort.readString(event.getEventValue());
                //System.out.println("Received response from port: " + receivedData);
                message = receivedData;
                Thread.sleep(100);
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
