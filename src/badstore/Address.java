package badstore;

public class Address {


    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zip +
                " (" + country + ")";
    }
}
