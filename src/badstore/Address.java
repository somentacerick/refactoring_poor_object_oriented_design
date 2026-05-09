package badstore;

public class Address {

    //Fields need to be private to protect obj data from changing
    // addresses should not change after creation so we use final

    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String country;

    //constructor to enforce invariants, prevents Address obj from being created
    //into an invalid state
    public Address(String street, String city, String state, String zip, String country {
        
    })

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zip +
                " (" + country + ")";
    }
}
