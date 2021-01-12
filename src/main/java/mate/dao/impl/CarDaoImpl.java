package mate.dao.impl;

import java.util.ArrayList;
import java.util.List;
import mate.dao.CarDao;
import mate.db.Storage;
import mate.lib.Dao;
import mate.model.Car;
import mate.model.Driver;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Car get(Long id) {
        return Storage.cars.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Car not exist!"));
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
    public void addDriverToCar(Driver driver, Car car) {
        List<Driver> cars = car.getDrivers();
        cars.add(driver);
        car.setDrivers(cars);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        List<Driver> drivers = new ArrayList<>();
        for (int i = 0; i < car.getDrivers().size(); i++) {
            if (!car.getDrivers().get(i).equals(driver)) {
                drivers.add(car.getDrivers().get(i));
            }
        }
        car.setDrivers(drivers);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> cars = new ArrayList<>();
        Driver driver = null;
        for (int i = 0; i < Storage.drivers.size(); i++) {
            if (Storage.drivers.get(i).getId().equals(driverId)) {
                driver = Storage.drivers.get(i);
            }
        }
        for (int i = 0; i < Storage.cars.size(); i++) {
            for (int j = 0; j < Storage.cars.get(i).getDrivers().size(); j++) {
                if (Storage.cars.get(i).getDrivers().get(j).equals(driver)) {
                    cars.add(Storage.cars.get(i));
                }
            }
        }
        return cars;
    }
}
