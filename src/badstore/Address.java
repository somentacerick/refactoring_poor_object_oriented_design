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
    public Address(String street, String city, String state, String zip, String country) {
        requireNonBlank(street, "Street");
        requireNonBlank(city, "City");
        requireNonBlank(state, "State");
        requireNonBlank(zip, "Zip");
        requireNonBlank(country, "Country");

        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    //helper method, avoids repeated if statements, more readable
    private void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank.");
        }
    }

    //getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    //encapsulate logic related to address location
    // prevents country checks somewhere else
    public boolean isInternational() {
        return !"USA".equalsIgnoreCase(country);
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zip +
                " (" + country + ")";

    }
}
