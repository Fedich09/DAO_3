package mate.service;

import java.util.List;
import java.util.Optional;
import mate.model.Manufacturer;

public interface ManufacturerService {
    Manufacturer add(Manufacturer manufacturer);

    Optional<Manufacturer> getById(Long id);

    Manufacturer update(Manufacturer object);

    boolean delete(Long id);

    List<Manufacturer> getAll();
}
