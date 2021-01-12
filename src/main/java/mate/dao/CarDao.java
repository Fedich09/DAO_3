package mate.dao;

import java.util.List;
import mate.model.Car;
import mate.model.Driver;

public interface CarDao {
    Car create(Car car);

    Car get(Long id);
    
    List<Car> getAll();

    Car update(Car car);

    boolean delete(Long id);

    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

    List<Car> getAllByDriver(Long driverId);
}
