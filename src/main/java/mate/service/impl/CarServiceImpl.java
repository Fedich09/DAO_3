package mate.service.impl;

import java.util.List;
import mate.dao.CarDao;
import mate.lib.Inject;
import mate.lib.Service;
import mate.model.Car;
import mate.model.Driver;
import mate.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    CarDao carDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
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
        carDao.addDriverToCar(driver, car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        carDao.removeDriverFromCar(driver, car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return carDao.getAllByDriver(driverId);
    }
}
