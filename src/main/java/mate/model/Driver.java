package mate.model;

public class Driver {
    private Long id;
    private String name;
    private String licenceNumber;

    public Driver(String name, String licenceNumber) {
        this.name = name;
        this.licenceNumber = licenceNumber;
    }

    @Override
    public String toString() {
        return "Driver{" + "id="
                + id + ", name='"
                + name + '\'' + ", licenceNumber='"
                + licenceNumber + '\'' + '}';
    }
}
