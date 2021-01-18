package mate.service;

import java.util.List;
import mate.model.Manufacturer;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Manufacturer get(Long id);

    Manufacturer update(Manufacturer object);

    boolean delete(Long id);

    List<Manufacturer> getAll();
}
