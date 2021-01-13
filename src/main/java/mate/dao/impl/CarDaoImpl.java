package mate.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.dao.CarDao;
import mate.db.Storage;
import mate.lib.Dao;
import mate.model.Car;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        return Storage.cars.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        for (int i = 0; i < Storage.cars.size(); i++) {
            if (Storage.cars.get(i).getId().equals(car.getId())) {
                Storage.cars.set(i, car);
            }
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return Storage.cars.stream()
                .filter(c -> c.getDrivers().stream()
                        .anyMatch(d -> d.getId() != null && d.getId().equals(driverId)))
                .collect(Collectors.toList());
    }
}
