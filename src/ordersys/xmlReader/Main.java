package ordersys.xmlReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
//import org.xml.sax.SAXParseException;

/**
 * Created by ian on 02/05/2017.
 */
public class Main {

    public static void main(String[] args) {
//    try {
//        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//        Document doc = docBuilder.parse (new File("voorbeeldorder.xml"));
//
//        String orderNr = doc.getElementsByTagName("ordernummer").item(0).getTextContent();
//        String date = doc.getElementsByTagName("datum").item(0).getTextContent();
//
//        String firstName = doc.getElementsByTagName("voornaam").item(0).getTextContent();
//        String lastName = doc.getElementsByTagName("achternaam").item(0).getTextContent();
//        String address = doc.getElementsByTagName("adres").item(0).getTextContent();
//        String zipCode = doc.getElementsByTagName("postcode").item(0).getTextContent();
//        String city = doc.getElementsByTagName("plaats").item(0).getTextContent();
//
//        NodeList listOfProducts = doc.getElementsByTagName("artikelnr");
//        int totalProducts = listOfProducts.getLength();
//
//        System.out.println("Bestelling #" + orderNr + " - datum:" + date + "\n");
//        System.out.println("Op naam van:\n" + firstName + " " + lastName + "\n");
//        System.out.println(address + "\n" + zipCode + " " + city + "\n\n");
//        System.out.println(totalProducts + " besteld: \n");
//        for(int s=0; s<listOfProducts.getLength() ; s++) {
//
//            String product = listOfProducts.item(s).getTextContent();
//            System.out.println(product);
//        }
//    } catch (FileNotFoundException e) {
//        e.printStackTrace();
//    } catch (ParserConfigurationException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    } catch (SAXException e) {
//        e.printStackTrace();
//    } catch (NullPointerException e) {
//        e.printStackTrace();
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
    }
}
