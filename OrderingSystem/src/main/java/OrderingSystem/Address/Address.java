package OrderingSystem.Address;

public class Address {
    private final String street;
    private final String city;

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}
