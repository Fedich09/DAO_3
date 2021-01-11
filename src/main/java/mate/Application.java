package mate;

import java.util.List;
import mate.lib.Injector;
import mate.model.Manufacturer;
import mate.service.impl.ManufacturerService;

public class Application {
    private static Injector injector = Injector
            .getInstance("mate");

    public static void main(String[] args) {
        ManufacturerService serviceManufacturer =
                (ManufacturerService) injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturer = new Manufacturer("BMW", "Germany");
        Manufacturer manufacturer1 = new Manufacturer("Mercedes", "Germany");
        Manufacturer manufacturer2 = new Manufacturer("Mitsubishi", "Japan");
        serviceManufacturer.add(manufacturer);
        serviceManufacturer.add(manufacturer2);
        serviceManufacturer.update(1L, manufacturer1);
        serviceManufacturer.deleteById(1L);
        serviceManufacturer.add(manufacturer);
        serviceManufacturer.delete(manufacturer);
        List<Manufacturer> all = serviceManufacturer.getAll();
        System.out.println(all.toString());
        System.out.println();
    }
}
