package mate.security.impl;

import mate.exception.AuthenticationException;
import mate.lib.Inject;
import mate.lib.Service;
import mate.model.Driver;
import mate.security.AuthenticationService;
import mate.service.DriverService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driver = driverService.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Incorrect user name or password"));
        if (driver.getPassword().equals(password)) {
            return driver;
        }
        throw new AuthenticationException("Incorrect user name or password");
    }
}
