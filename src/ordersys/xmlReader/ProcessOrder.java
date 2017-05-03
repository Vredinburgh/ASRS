package ordersys.xmlReader;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by ian on 02/05/2017.
 */
public class ProcessOrder {

    private String orderNr, date, firstName, lastName, address, zipCode, city;
    private int[] producten;

    public ProcessOrder(String pathname) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File(pathname));

            orderNr = doc.getElementsByTagName("ordernummer").item(0).getTextContent();
            date = doc.getElementsByTagName("datum").item(0).getTextContent();

            firstName = doc.getElementsByTagName("voornaam").item(0).getTextContent();
            lastName = doc.getElementsByTagName("achternaam").item(0).getTextContent();
            address = doc.getElementsByTagName("adres").item(0).getTextContent();
            zipCode = doc.getElementsByTagName("postcode").item(0).getTextContent();
            city = doc.getElementsByTagName("plaats").item(0).getTextContent();

            NodeList listOfProducts = doc.getElementsByTagName("artikelnr");
            int totalProducts = listOfProducts.getLength();

            for(int s=0; s<listOfProducts.getLength() ; s++) {
                producten[s] = Integer.parseInt(listOfProducts.item(s).getTextContent());
            }

            System.out.println("Bestelling #" + orderNr + " - datum:" + date + "\n");
            System.out.println("Op naam van:\n" + firstName + " " + lastName + "\n");
            System.out.println(address + "\n" + zipCode + " " + city + "\n\n");
            System.out.println(totalProducts + " besteld: \n");
            for(int s=0; s<listOfProducts.getLength() ; s++) {

                String product = listOfProducts.item(s).getTextContent();
                System.out.println(product);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
