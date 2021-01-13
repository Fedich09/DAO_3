package mate.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.dao.CarDao;
import mate.lib.Inject;
import mate.lib.Service;
import mate.model.Car;
import mate.model.Driver;
import mate.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private CarDao carDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Optional<Car> get(Long id) {
        return carDao.get(id);
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
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
        return carDao.getAllByDriver(driverId);
    }
}
