package mate.model;

import java.util.Objects;

public class Manufacturer {
    private Long id;
    private String name;
    private String country;

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manufacturer{" + "id="
                + id + ", name='"
                + name + '\'' + ", country='"
                + country + '\'' + '}';
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object manufacturer) {
        if (this == manufacturer) {
            return true;
        }
        if (manufacturer == null || getClass() != manufacturer.getClass()) {
            return false;
        }
        Manufacturer that = (Manufacturer) manufacturer;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
