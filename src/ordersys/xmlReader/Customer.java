package ordersys.xmlReader;

import java.util.ArrayList;

/**
 * Created by ian on 03/05/2017.
 */
public class Customer {

    private String orderNr, date, firstName, lastName, address, zipCode, city;

    public Customer(ArrayList<String> details) {

        orderNr = details.get(0);
        date = details.get(1);
        firstName = details.get(2);
        lastName = details.get(3);
        address = details.get(4);
        zipCode = details.get(5);
        city = details.get(6);
    }

    public String getOrderNr() {
        return orderNr;
    }

    public String getDate() {
        return date;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "orderNr='" + orderNr + '\'' +
                ", date='" + date + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
