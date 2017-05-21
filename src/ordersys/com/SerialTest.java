package ordersys.com;

/**
 * Created by ian on 04/05/2017.
 *
 * https://sourceforge.net/projects/javaarduinolibrary/files/
 */

import jssc.SerialPortException;

public class SerialTest {



    public static void main(String[] args) {

        int[][] kaas = new int[][]{
                { 2, 3 },
                { 5, 4 },
                { 3, 1 }

        };

        /*InstructionsGenerator instructionsGenerator = new InstructionsGenerator();
        instructionsGenerator.newInstructions(kaas);
        System.out.println(instructionsGenerator.getInstructions());*/

        for(int i = 0; i < PortFinder.portNames.length; i++) {
            System.out.println(PortFinder.portNames[i]);
        }

        Transporter transporter = null;
        try {
            transporter = new Transporter(PortFinder.portNames[0]);
            transporter.command("2");
            System.out.println("START");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}