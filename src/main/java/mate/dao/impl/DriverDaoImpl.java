package mate.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.dao.DriverDao;
import mate.db.Storage;
import mate.lib.Dao;
import mate.model.Driver;

@Dao
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        Storage.addMDriver(driver);
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Storage.drivers.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }

    @Override
    public Driver update(Driver driver) {
        for (int i = 0; i < Storage.drivers.size(); i++) {
            if (Storage.drivers.get(i).getId().equals(driver.getId())) {
                Storage.drivers.set(i, driver);
            }
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.removeIf(e -> e.getId().equals(id));
    }
}
