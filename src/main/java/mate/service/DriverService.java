package mate.service;

import java.util.List;
import java.util.Optional;
import mate.model.Driver;

public interface DriverService {
    Driver create(Driver driver);

    Optional<Driver> get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);
}
