package mate.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.db.Storage;
import mate.lib.Dao;
import mate.model.Manufacturer;

@Dao
public class ManufacturerDaoImpl implements mate.dao.ManufacturerDao {
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
        Manufacturer old = new Manufacturer();
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (manufacturer.getId().equals(Storage.manufacturers.get(i).getId())) {
                old = Storage.manufacturers.get(i);
                Storage.manufacturers.set(i, manufacturer);
            }
        }
        return old;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.manufacturers.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        return Storage.manufacturers.removeIf(e -> e.equals(manufacturer));
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }
}
