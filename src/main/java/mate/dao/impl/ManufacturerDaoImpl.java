package mate.dao.impl;


import mate.db.Storage;
import mate.lib.Dao;
import mate.model.Manufacturer;

import java.util.List;
import java.util.Optional;

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
    public Manufacturer update(Long id, Manufacturer manufacturer) {
        Storage.manufacturers.set(id.intValue(), manufacturer);
        return manufacturer;
    }

    @Override
    public boolean deleteById(Long id) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (Storage.manufacturers.get(i).getId().equals(id)) {
                Storage.manufacturers.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (Storage.manufacturers.get(i).equals(manufacturer)) {
                Storage.manufacturers.remove(manufacturer);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }
}
