package mate;

import java.util.Arrays;
import mate.lib.Injector;
import mate.model.Car;
import mate.model.Driver;
import mate.model.Manufacturer;
import mate.service.CarService;
import mate.service.DriverService;
import mate.service.ManufacturerService;

public class Application {
    private static Injector injector = Injector
            .getInstance("mate");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);
        manufacturerService.add(new Manufacturer("bmv", "german"));
        System.out.println(manufacturerService.getById(22L));
        Manufacturer manufacturer = new Manufacturer("zaz", "UA");
        manufacturer.setId(1L);
        System.out.println(manufacturerService.update(manufacturer));
        System.out.println(manufacturerService.delete(3L));
        System.out.println(manufacturerService.getAll());

        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        driverService.create(new Driver("Vova", "ab123"));
        driverService.create(new Driver("Dima", "cd456"));
        driverService.create(new Driver("Max", "ef789"));
        driverService.delete(1L);
        driverService.delete(2L);
        Driver driver1 = new Driver("driver1", "licence");
        Driver driver2 = new Driver("driver2", "licence");
        driver1.setId(3L);
        driverService.create(driver2);
        driverService.update(driver1);
        System.out.println(Arrays.toString(driverService.getAll().toArray(new Driver[0])));

        Manufacturer manufacturer1 = new Manufacturer("manu1", "country1");
        Manufacturer manufacturer2 = new Manufacturer("manu2", "country2");
        Manufacturer manufacturer3 = new Manufacturer("manu3", "country3");
        Driver driver10 = new Driver("Vova", "ab123");
        Driver driver20 = new Driver("Dima", "cd456");
        Driver driver30 = new Driver("Max", "ef789");
        Car car1 = new Car("model1", manufacturer1);
        Car car2 = new Car("model2", manufacturer2);
        Car car3 = new Car("model3", manufacturer3);
        CarService carService = (CarService) injector.getInstance(CarService.class);
        carService.create(car1);
        carService.create(car2);
        carService.create(car3);
        carService.addDriverToCar(driver10, car1);
        carService.addDriverToCar(driver20, car2);
        carService.addDriverToCar(driver30, car3);
        Manufacturer manufacturer4 = new Manufacturer("manu4", "country4");
        Driver driver40 = new Driver("Petro", "asg124");
        driver40.setId(4L);
        driverService.update(driver40);
        Car car4 = new Car("model4", manufacturer4);
        car4.setId(3L);
        carService.delete(1L);
        carService.delete(2L);
        carService.update(car4);
        carService.addDriverToCar(driver40, car4);
        carService.addDriverToCar(new Driver("Bob", "uio114"), car4);
        System.out.println(Arrays.toString(carService.getAll().toArray(new Car[0])));
        System.out.println(Arrays.toString(carService.getAllByDriver(4L).toArray(new Car[0])));
        carService.removeDriverFromCar(driver40, car4);
        System.out.println(Arrays.toString(carService.getAllByDriver(4L).toArray(new Car[0])));
    }
}
