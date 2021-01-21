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
        CarService carService = (CarService) injector.getInstance(CarService.class);
        DriverService driverService = (DriverService) injector
                .getInstance(DriverService.class);
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);
        Car car = carService.get(2L);
        List<Driver> drivers = driverService.getAll().stream()
                .filter(i -> i.getId() % 10 == 0)
                .collect(Collectors.toList());
        car.setDrivers(drivers);
        carService.update(car);
    }
}
