package mate.dao;

import java.util.List;
import java.util.Optional;
import mate.model.Manufacturer;

public interface ManufacturerDao {
    Manufacturer add(Manufacturer manufacturer);

    Optional<Manufacturer> getById(Long id);

    Manufacturer update(Manufacturer object);

    boolean deleteById(Long id);

    boolean delete(Manufacturer manufacturer);

    List<Manufacturer> getAll();
}
