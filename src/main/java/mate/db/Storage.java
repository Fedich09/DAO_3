package mate.db;

import mate.model.Car;
import mate.model.Driver;
import mate.model.Manufacturer;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Car> cars = new ArrayList<>();
    public static List<Driver> drivers = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    private static Long manufactureId = 0L;
    private static Long driversId = 0L;
    private static Long carsId = 0L;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufactureId);
        manufacturers.add(manufacturer);
    }
}
