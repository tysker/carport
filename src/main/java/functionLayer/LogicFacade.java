/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import dbAccess.CarportMapper;
import dbAccess.CustomerMapper;
import functionLayer.calculation.CarportFlatProductListe;
import dbAccess.ProductMapper;
import dbAccess.UserMapper;
import exceptions.FogException;
import functionLayer.calculation.CarportPointedRoofProductListe;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.decimal4j.util.DoubleRounder;

/**
 *
 * @author oerte
 */
public class LogicFacade {

    // ------------ LOGICFACADE METHODS FOR DATABASE -----------------
    public static User login(String email, String password) throws FogException {
        UserMapper um = new UserMapper();
        return um.getUser(email, password);
    }

    /**
     * This method will create a hashed password and return it, with help of
     * userns password.
     * The MD5 Message-Digest Algorithm is a cryptographic hash function that produces a 128-bit (16-byte) hash value.
     * The basic idea is to map data sets of variable length to data sets of a fixed length.
     * In order to do this, the input message is split into chunks of 512-bit blocks. A padding is added to the end so that it’s length can be divided by 512. 
     * Now, these blocks are processed by the MD5 algorithm, which operates in a 128-bit state, and the result will be a 128-bit hash value. 
     * After applying MD5, generated hash is typically a 32-digit hexadecimal number.
     *
     * @param password Userns password.
     * @return Returns a new hashed password String
     */
    public static String createHashedPassword(String password) {
        String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage() + " " + LogicFacade.class.getName());
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static User createUser(String email, String password, int customerID) throws FogException {
        User user = new User(password, email, "customer", customerID);
        UserMapper um = new UserMapper();
        um.createUser(user);
        return user;
    }

    public static Customer getCustomerByEmail(String email) {
        CustomerMapper cm = new CustomerMapper();
        Customer customer = cm.getCustomerByEmail(email);
        return customer;

    }

    public static void addCustomer(Customer customer) {
        CustomerMapper cm = new CustomerMapper();
        cm.addCustomer(customer);

    }

    public static int createCustomer(Customer customer) {
        CustomerMapper cm = new CustomerMapper();
        int id = cm.addCustomerAndUser(customer);
        return id;
    }

    public static void addCarport(Carport carport, Shed shed) {
        CarportMapper cm = new CarportMapper();
        cm.addCarport(carport, shed);
    }

    public static Shed addShed(Shed shed) {
        CarportMapper cm = new CarportMapper();
        cm.addShed(shed);
        return shed;
    }

    public static List<Carport> getOrdresFromCarportByEnum(String enumValue) {
        CarportMapper cm = new CarportMapper();
        List<Carport> carportList = cm.getCarportByStatus(enumValue);
        return carportList;
    }

    public static List<Carport> getAllCarportOrders() {
        CarportMapper cm = new CarportMapper();
        List<Carport> carportList = cm.getAllCarportOrders();
        return carportList;
    }

    public static void updateOrderStatus(String status, int id) {
        CarportMapper cm = new CarportMapper();
        cm.updateOrderStatus(status, id);
    }

    public static void addProduct(Product product) {
        ProductMapper pm = new ProductMapper();
        pm.addProducts(product);
    }

    public static List<Product> getAllProductsFromDatabase() {
        ProductMapper pm = new ProductMapper();
        List<Product> produktList = pm.allProducts();
        return produktList;
    }

    public static List<Product> searchInDatabaseProductTable(String value) {
        ProductMapper pm = new ProductMapper();
        List<Product> searchList = pm.searchInProductTable(value);
        return searchList;
    }

    public static List<Product> remOrderedByLength() {
        ProductMapper pm = new ProductMapper();
        List<Product> remList = pm.orderByLengthRem();
        return remList;
    }

    public static void updatePrice(int id, double price) {
        ProductMapper pm = new ProductMapper();
        pm.updatePrice(id, price);
    }

    public static void deleteProduct(int productID) {
        ProductMapper pm = new ProductMapper();
        pm.deleteProduct(productID);
    }

    public static Carport getCarportByID(int caportID) {
        CarportMapper cm = new CarportMapper();
        Carport carport = cm.getCarportById(caportID);
        return carport;
    }

    public static Customer getCustomerByID(int customerID) {
        CustomerMapper cm = new CustomerMapper();
        Customer customer = cm.getCustomerByID(customerID);
        return customer;
    }

    // -----------------CARPORT CALCULATIONS LOGICFACADE --------------------------------
    /**
     * This methode is called in, CreateOrderFlatRoof.java in presentationLayer,
     * to get the total price of the carport, what is then used to create a
     * carport in the Database, OrderRequestFlatRoof.java in presentationLayer,
     * to get the total price of the carport, what is used to show the price at
     * orderRequestFlatRoof.jsp, SeeOrderProductList.java in presentationLayer,
     * to show a List of parts for an specifik customer by Id.
     *
     * @param length Length of the carport from carportFlatRood.jsp
     * @param width Width of the carport from carportFlatRood.jsp
     * @param roofMaterial Roof material is choosen from the client through
     * carportFlarRoof.jsp or carportPointedRoof.jsp.
     * @return Returns a list of all products, that is needed to build a carport
     * with a flat roof.
     *
     */
    public static List<Product> carportCalculaterFlatRoof(double length, double width, String roofMaterial) {
        CarportFlatProductListe cfp = new CarportFlatProductListe();
        List<Product> list = cfp.carportCalculaterFlatRoof(length, width, roofMaterial);
        return list;
    }

    /**
     * This methode is called in, CreateOrderFlatRoof.java in presentationLayer,
     * to get the total price of the carport, what is then used to create a
     * carport in the Database, OrderRequestFlatRoof.java in presentationLayer,
     * to get the total price of the carport, what is used to show the price at
     * orderRequestFlatRoof.jsp, SeeOrderProductList.java in presentationLayer,
     * to show a List of parts for an specifik customer by Id.
     *
     * @param length Length of the carport from carportFlatRoof.jsp.
     * @param width Width of the carport from carportFlatRoof.jsp.
     * @param shedLength Length of shed from carportFlatRoof.jsp.
     * @param shedWidth Width of shed from carportFlatRoof.jsp.
     * @param roofMaterial Roof material is choosen from the client through
     * carportFlarRoof.jsp or carportPointedRoof.jsp.
     * @return Returns a list of all products, that is needed to build a carport
     * with a flat roof and shed.
     *
     */
    public static List<Product> carportCalculaterFlatRoofIncludingShed(double length, double width, double shedLength, double shedWidth, String roofMaterial) {
        CarportFlatProductListe cfp = new CarportFlatProductListe();
        List<Product> list = cfp.carportCalculaterFlatRoofIncludingShed(length, width, shedLength, shedWidth, roofMaterial);
        return list;
    }

    /**
     * This methode is called in, CreateOrderPointedRoof.java in
     * presentationLayer, to get the total price of the carport, what is then
     * used to create a carport in the Database, OrderRequestPointedRoof.java in
     * presentationLayer, to get the total price of the carport, what is used to
     * show the price at orderRequestPointedRoof.jsp, SeeOrderProductList.java
     * in presentationLayer, to show a List of parts for an specifik customer by
     * Id.
     *
     * @param length Length of the carport from carportPointedRoodf.jsp.
     * @param width Width of the carport from carportPointedRoof.jsp.
     * @param degree Degree of the carports Pointed roof from
     * carportPointedRoof.jsp.
     * @param roofMaterial Roof material is choosen from the client through
     * carportFlarRoof.jsp or carportPointedRoof.jsp.
     * @return Returns a list of all products, that is needed to build a carport
     * with a Pointed roof.
     */
    public static List<Product> carportCalculatorPointedRoof(double length, double width, double degree, String roofMaterial) {
        CarportPointedRoofProductListe cfp = new CarportPointedRoofProductListe();
        List<Product> list = cfp.carportCalculaterPointedRoof(length, width, degree, roofMaterial);
        return list;
    }

    /**
     * This methode is called in, CreateOrderPointedRoof.java in
     * presentationLayer, to get the total price of the carport, what is then
     * used to create a carport in the Database, OrderRequestPointedRoof.java in
     * presentationLayer, to get the total price of the carport, what is used to
     * show the price at orderRequestPointedRoof.jsp, SeeOrderProductList.java
     * in presentationLayer, to show a List of parts for an specifik customer by
     * Id.
     *
     * @param length Length of the carport from carportPointedRoodf.jsp.
     * @param width Width of the carport from carportPointedRoof.jsp.
     * @param degree Degree of the carports Pointed roof from
     * carportPointedRoof.jsp.
     * @param shedLength Length of shed from carportPointedRood.jsp.
     * @param shedWidth Width of shed from carportPointedRood.jsp.
     * @param roofMaterial Roof material is choosen from the client through
     * carportFlatRoof.jsp or carportPointedRoof.jsp.
     * @return Returns a list of all products, that is needed to build a carport
     * with a Pointed roof and shed.
     */
    public static List<Product> carportCalculatorPointedRoofIncludingShed(double length, double width, double degree, double shedLength, double shedWidth, String roofMaterial) {
        CarportPointedRoofProductListe cfp = new CarportPointedRoofProductListe();
        List<Product> list = cfp.carportCalculaterPointedRoofIncludingShed(length, width, degree, length, width, roofMaterial);
        return list;
    }

    /**
     * This method with help of javas DecimalFormater, rounds a double to two
     * decimal after the comma, This methode is called in, LogicFacade.java in
     * functionLayer, in method totalPriceOfCarport, Product.java in
     * functionLayer, in method getPriceLine.
     *
     * @param value is a price value
     * @return Returns a double with two decimals after the comma.
     */
    public static double roundDoubleToTwoDecimalPoints(double value) {
//        String value_String =  String.format("%.2f", value);
//        double newValue = Double.parseDouble(value_String);
//        return newValue;
        return DoubleRounder.round(value, 2);
    }

    /**
     * This method takes a list of parts, loop through the list and add each
     * priceLine of each product with eachother.
     *
     * @param stykliste Is a specific list of an Carport with all products
     * needed to build it.
     * @return Returns the total price of an Carport.
     */
    public static double totalPriceOfCarport(List<Product> stykliste) {
        double totalPriceOfCarport = 0;
        for (Product produkt : stykliste) {
            totalPriceOfCarport += produkt.getTotalPriceOfOrder();
        }
        //return totalPriceOfCarport;
        return roundDoubleToTwoDecimalPoints(totalPriceOfCarport);
    }

}
