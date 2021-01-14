package mate.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.dao.ManufacturerDao;
import mate.db.Storage;
import mate.model.Manufacturer;

public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer add(Manufacturer manufacturer) {
        Storage.addManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> getById(Long id) {
        return Storage.manufacturers.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (manufacturer.getId().equals(Storage.manufacturers.get(i).getId())) {
                Storage.manufacturers.set(i, manufacturer);
            }
        }
        return manufacturer;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.manufacturers.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }
}
