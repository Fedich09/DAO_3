package mate.dao.jdbc;

import java.util.Optional;

import mate.lib.Dao;
import mate.model.Driver;
import mate.service.AuthenticationService;

@Dao
public class AuthenticationJdbcImpl implements AuthenticationService {

    @Override
    public Driver login(String login, String password) {
        return null;
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        return Optional.empty();
    }
}
