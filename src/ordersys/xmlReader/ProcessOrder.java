package ordersys.xmlReader;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ian on 02/05/2017.
 */
public class ProcessOrder {

    private ArrayList<String> details = new ArrayList<>();
    private int[] products;

    public ProcessOrder(String pathname) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File(pathname));

            details.add(doc.getElementsByTagName("ordernummer").item(0).getTextContent());  //orderNr
            details.add(doc.getElementsByTagName("datum").item(0).getTextContent());        //date

            details.add(doc.getElementsByTagName("voornaam").item(0).getTextContent());     //firstName
            details.add(doc.getElementsByTagName("achternaam").item(0).getTextContent());   //lastName
            details.add(doc.getElementsByTagName("adres").item(0).getTextContent());        //address
            details.add(doc.getElementsByTagName("postcode").item(0).getTextContent());     //zipCode
            details.add(doc.getElementsByTagName("plaats").item(0).getTextContent());       //city

            NodeList listOfProducts = doc.getElementsByTagName("artikelnr");
            products = new int[listOfProducts.getLength()];
            for(int s=0; s<listOfProducts.getLength() ; s++) {
                products[s] = Integer.parseInt(listOfProducts.item(s).getTextContent());
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public int[] getProducts() {
        return products;
    }
}
