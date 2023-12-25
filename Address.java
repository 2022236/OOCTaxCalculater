/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOCTaxCalculater;

/**
 *
 * @author Lizandra Matos 2022336 and Taciana Oliveira 2022404
 */
public class Address {
   // Private attributes representing address information
    private String Country;
    private String State;
    private String City;
    private int ZIPCode;
    private String StreetName;
    private int StreetNumber;

    // Constructor method that initializes an Address object with provided values
    public Address(String retrievedCountry,
            String retrievedState,
            String retrievedCity,
            int retrievedZIPCode,
            String retrievedStreetName,
            int retrievedStreetNumber) {
        
        // Atribuindo os valores recuperados aos atributos correspondentes do objeto Address
        this.Country = retrievedCountry;
        this.State = retrievedState;
        this.City = retrievedCity;
        this.ZIPCode = retrievedZIPCode;
        this.StreetName = retrievedStreetName;
        this.StreetNumber = retrievedStreetNumber;
    }

    public String getCountry() {
        return Country;
    }

    public String getState() {
        return State;
    }

    public String getCity() {
        return City;
    }

    public int getZIPCode() {
        return ZIPCode;
    }

    public String getStreetName() {
        return StreetName;
    }

    public int getStreetNumber() {
        return StreetNumber;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setZIPCode(int ZIPCode) {
        this.ZIPCode = ZIPCode;
    }

    public void setStreetName(String StreetName) {
        this.StreetName = StreetName;
    }

    public void setStreetNumber(int StreetNumber) {
        this.StreetNumber = StreetNumber;
    }

    public String getCompleteAddress() {
        return "Country: " + Country + "\n"
                + "State: " + State + "\n"
                + "City: " + City + "\n"
                + "ZIP Code: " + ZIPCode + "\n"
                + "Street Name: " + StreetName + "\n"
                + "Street Number: " + StreetNumber;
    }

}
