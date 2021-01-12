package mate.db;

import java.util.ArrayList;
import java.util.List;
import mate.model.Manufacturer;

public class Storage {
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    private static Long manufactureId = 0L;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufactureId);
        manufacturers.add(manufacturer);
    }
}
