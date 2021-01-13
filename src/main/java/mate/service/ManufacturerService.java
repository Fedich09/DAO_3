package mate.service;

import mate.model.Manufacturer;
import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    Manufacturer add(Manufacturer manufacturer);

    Optional<Manufacturer> getById(Long id);

    Manufacturer update(Manufacturer object);

    boolean delete(Long id);

    List<Manufacturer> getAll();
}
