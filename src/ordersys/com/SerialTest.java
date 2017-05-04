package ordersys.com;

/**
 * Created by ian on 04/05/2017.
 *
 * https://sourceforge.net/projects/javaarduinolibrary/files/
 */
import java.util.Scanner;

import arduino.*;

public class SerialTest {

    public static void main(String[] args) {

        Transporter transporter = new Transporter();
        Sorter sorter = new Sorter();
        transporter.openConnection();

        int[] route = {1,3,5,4};
        transporter.start(route);



    }

}