package mate.service;

import java.util.List;
import mate.model.Manufacturer;

public interface ManufacturerService {
    Manufacturer add(Manufacturer manufacturer);

    Manufacturer getById(Long id);

    Manufacturer update(Manufacturer manufacturer);

    boolean deleteById(Long id);

    boolean delete(Manufacturer manufacturer);

    List<Manufacturer> getAll();
}
