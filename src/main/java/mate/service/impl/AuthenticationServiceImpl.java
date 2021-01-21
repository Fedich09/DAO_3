package mate.service.impl;

import java.util.Optional;

import mate.dao.AuthenticationDao;
import mate.lib.Inject;
import mate.lib.Service;
import mate.model.Driver;
import mate.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    AuthenticationDao authenticationDao;

    @Override
    public Driver login(String login, String password) {
        return authenticationDao.login(login, password);
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        return Optional.empty();
    }
}
