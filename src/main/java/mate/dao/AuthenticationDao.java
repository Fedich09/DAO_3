package mate.dao;

import java.util.Optional;

import mate.model.Driver;

public interface AuthenticationDao {
    Driver login(String login, String password);

    Optional<Driver> findByLogin(String login);
}
