package mate.service.impl;

import mate.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    Manufacturer add(Manufacturer manufacturer);

    Manufacturer getById(Long id);

    Manufacturer update(Manufacturer manufacturer);

    boolean deleteById(Long id);

    boolean delete(Manufacturer manufacturer);

    List<Manufacturer> getAll();
}
