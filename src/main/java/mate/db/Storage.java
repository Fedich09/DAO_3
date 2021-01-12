package mate.db;

import java.util.ArrayList;
import java.util.List;
import mate.model.Car;
import mate.model.Driver;
import mate.model.Manufacturer;

public class Storage {
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Driver> drivers = new ArrayList<>();
    public static List<Car> cars = new ArrayList<>();
    private static Long manufactureId = 0L;
    private static Long driversId = 0L;
    private static Long carsId = 0L;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufactureId);
        manufacturers.add(manufacturer);
    }

    public static void addMDriver(Driver driver) {
        driver.setId(++driversId);
        drivers.add(driver);
    }

    public static void addCar(Car car) {
        car.setId(++carsId);
        cars.add(car);
    }
}
