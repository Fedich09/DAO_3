package mate.dao;

import java.util.Optional;
import mate.model.Driver;

public interface DriverDao extends GenericDao<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
