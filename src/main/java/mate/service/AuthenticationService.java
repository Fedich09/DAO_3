package mate.service;

import java.util.Optional;

import mate.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password);

    Optional<Driver> findByLogin(String login);
}
