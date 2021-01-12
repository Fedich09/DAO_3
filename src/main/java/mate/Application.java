package mate;

import java.util.Arrays;
import mate.lib.Injector;
import mate.model.Manufacturer;
import mate.service.impl.ManufacturerService;

public class Application {
    private static Injector injector = Injector
            .getInstance("mate");

    public static void main(String[] args) {
        ManufacturerService serviceManufacturer =
                (ManufacturerService) injector.getInstance(ManufacturerService.class);
        serviceManufacturer.add(new Manufacturer("bmw1", "UK1")); // id = 1
        serviceManufacturer.add(new Manufacturer("bmw2", "UK2")); // id = 2
        serviceManufacturer.add(new Manufacturer("bmw3", "UK3")); // id = 3
        serviceManufacturer.add(new Manufacturer("bmw4", "UK4")); // id = 4
        serviceManufacturer.deleteById(1L);
        serviceManufacturer.deleteById(2L);
        Manufacturer manufacturer5 = new Manufacturer("bmw5", "UK5"); // id = 5
        Manufacturer manufacturer = new Manufacturer("bmw51", "UK55"); // id = 5
        manufacturer.setId(5L);
        serviceManufacturer.add(manufacturer5);
        serviceManufacturer.update(manufacturer);
        System.out.println((Arrays.toString(serviceManufacturer
                .getAll().toArray(new Manufacturer[0]))));
    }
}
