package mate;

import java.util.List;
import java.util.stream.Collectors;
import mate.lib.Injector;
import mate.model.Car;
import mate.model.Driver;
import mate.service.CarService;
import mate.service.DriverService;
import mate.service.ManufacturerService;

public class Application {
    private static Injector injector = Injector
            .getInstance("mate");

    public static void main(String[] args) {
        ManufacturerService manufacturerService =
                (ManufacturerService) injector.getInstance(ManufacturerService.class);
        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        CarService carService = (CarService) injector.getInstance(CarService.class);
        List<Driver> drivers = driverService.getAll().stream()
                .filter(e -> e.getId() % 2 == 0)
                .collect(Collectors.toList());
        Car car = carService.get(25L);
        car.setManufacturer(manufacturerService.get(54L));
        car.setModel("new model");
        car.setId(45L);
        car.setDrivers(drivers);
        carService.create(car);
        carService.update(car);
        System.out.println(carService.get(25L));
        System.out.println(carService.getAllByDriver(2L));
        System.out.println(carService.delete(1L));

    }
}
