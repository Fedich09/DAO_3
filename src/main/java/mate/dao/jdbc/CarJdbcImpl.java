package mate.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.dao.CarDao;
import mate.exception.DataProcessingException;
import mate.lib.Dao;
import mate.model.Car;
import mate.model.Driver;
import mate.model.Manufacturer;
import mate.util.ConnectionUtil;

@Dao
public class CarJdbcImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars(model, manufacturer_id) VALUES(?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getModel());
            statement.setObject(2, car.getManufacturer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
            insertCarDrivers(car, connection);
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create this car " + car, e);
        }
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT "
                + "    c.car_id,"
                + "    c.model,"
                + "    m.manufacturer_id,"
                + "    m.manufacturer_name,"
                + "    m.manufacturer_country,"
                + "    cd.driver_id,"
                + "    d.name,"
                + "    d.license_number, "
                + "    d.login, "
                + "    d.password "
                + "FROM "
                + "    cars AS c"
                + "        LEFT JOIN"
                + "    manufacturers AS m ON c.manufacturer_id = m.manufacturer_id"
                + "        LEFT JOIN"
                + "    cars_drivers AS cd ON c.car_id = cd.car_id"
                + "        LEFT JOIN"
                + "    drivers AS d ON d.driver_id = cd.driver_id "
                + "    WHERE c.car_id = ? AND c.is_delete = false "
                + "AND m.is_delete = false "
                + "UNION SELECT "
                + "    c.car_id,"
                + "    c.model,"
                + "    m.manufacturer_id,"
                + "    m.manufacturer_name,"
                + "    m.manufacturer_country,"
                + "    cd.driver_id,"
                + "    d.name,"
                + "    d.license_number, "
                + "    d.login, "
                + "    d.password "
                + "FROM "
                + "    cars AS c"
                + "        RIGHT JOIN"
                + "    manufacturers AS m ON c.manufacturer_id = m.manufacturer_id"
                + "        RIGHT JOIN"
                + "    cars_drivers AS cd ON c.car_id = cd.car_id"
                + "        RIGHT JOIN"
                + "    drivers AS d ON d.driver_id = cd.driver_id"
                + "    WHERE c.car_id = ? AND c.is_delete = false "
                + "AND m.is_delete = false";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query,
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY)) {
            statement.setObject(1, id);
            statement.setObject(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = createNewCar(resultSet);
                List<Driver> drivers = getCarDrivers(car.getId(), resultSet);
                car.setDrivers(drivers);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get car by id " + id, e);
        }
        return Optional.ofNullable(car);
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT "
                + "    c.car_id,"
                + "    c.model,"
                + "    m.manufacturer_id,"
                + "    m.manufacturer_name,"
                + "    m.manufacturer_country,"
                + "    cd.driver_id,"
                + "    d.name,"
                + "    d.license_number, "
                + "    d.login, "
                + "    d.password "
                + "FROM "
                + "    cars AS c"
                + "        LEFT JOIN"
                + "    manufacturers AS m ON c.manufacturer_id = m.manufacturer_id"
                + "        LEFT JOIN"
                + "    cars_drivers AS cd ON c.car_id = cd.car_id"
                + "        LEFT JOIN"
                + "    drivers AS d ON d.driver_id = cd.driver_id "
                + "    WHERE c.is_delete = false and m.is_delete = false "
                + "UNION SELECT "
                + "    c.car_id,"
                + "    c.model,"
                + "    m.manufacturer_id,"
                + "    m.manufacturer_name,"
                + "    m.manufacturer_country,"
                + "    cd.driver_id,"
                + "    d.name,"
                + "    d.license_number, "
                + "    d.login, "
                + "    d.password "
                + "FROM "
                + "    cars AS c"
                + "        RIGHT JOIN"
                + "    manufacturers AS m ON c.manufacturer_id = m.manufacturer_id"
                + "        RIGHT JOIN"
                + "    cars_drivers AS cd ON c.car_id = cd.car_id"
                + "        RIGHT JOIN"
                + "    drivers AS d ON d.driver_id = cd.driver_id"
                + "    where c.is_delete = false and m.is_delete = false ";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query,
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            List<Car> cars = getCars(resultSet);
            for (Car car : cars) {
                car.setDrivers(getCarDrivers(car.getId(), resultSet));
            }
            return cars;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all cars", e);
        }
    }

    @Override
    public Car update(Car car) {
        String query = "UPDATE cars as c "
                + "SET "
                + "    c.model = ?,"
                + "    c.manufacturer_id = ? "
                + "WHERE"
                + "    c.car_id = ? "
                + "AND "
                + "     c.is_delete = FALSE ";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, car.getModel());
            statement.setObject(2, car.getManufacturer().getId());
            statement.setObject(3, car.getId());
            statement.executeUpdate();
            statement.close();
            deleteCarDrivers(car.getId(), connection);
            insertCarDrivers(car, connection);
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update this car " + car, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars "
                + "SET "
                + "    is_delete = TRUE "
                + "WHERE"
                + "    car_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete car by id " + id, e);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT "
                + "    c.car_id,"
                + "    c.model,"
                + "    m.manufacturer_id,"
                + "    m.manufacturer_name,"
                + "    m.manufacturer_country,"
                + "    cd.driver_id,"
                + "    d.name,"
                + "    d.license_number, "
                + "    d.login,"
                + "    d.password "
                + "FROM"
                + "    cars AS c"
                + "        JOIN"
                + "    manufacturers AS m ON c.manufacturer_id = m.manufacturer_id"
                + "        JOIN"
                + "    cars_drivers AS cd ON c.car_id = cd.car_id"
                + "        JOIN"
                + "    drivers AS d ON d.driver_id = cd.driver_id "
                + "WHERE d.driver_id = ? AND c.is_delete = FALSE"
                + "     AND d.is_delete = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query,
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY)) {
            statement.setObject(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            List<Car> cars = getCars(resultSet);
            for (Car car : cars) {
                car.setDrivers(getCarDrivers(car.getId(), resultSet));
            }
            return cars;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all by driver by this id " + driverId, e);
        }
    }

    private Car createNewCar(ResultSet resultSet) {
        try {
            Manufacturer manufacturer = new Manufacturer(resultSet
                    .getString("manufacturer_name"),
                    resultSet.getString("manufacturer_country"));
            manufacturer.setId(resultSet.getObject("manufacturer_id", Long.class));
            Car car = new Car(resultSet.getString("model"), manufacturer);
            car.setId(resultSet.getObject("car_id", Long.class));
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create car", e);
        }
    }

    private List<Car> getCars(ResultSet resultSet) {
        try {
            resultSet.first();
            List<Car> car = new ArrayList<>();
            car.add(createNewCar(resultSet));
            Car flag = car.get(0);
            resultSet.beforeFirst();
            while (resultSet.next()) {
                if (!flag.getId().equals(resultSet.getObject("car_id"))) {
                    car.add(createNewCar(resultSet));
                    flag = createNewCar(resultSet);
                }
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create new car", e);
        }
    }

    private List<Driver> getCarDrivers(Long id, ResultSet resultSet) {
        try {
            List<Driver> drivers = new ArrayList<>();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                Long driverId = resultSet.getObject("driver_id", Long.class);
                if (driverId == null) {
                    break;
                }
                Driver driver = new Driver(resultSet.getString("name"),
                        resultSet.getString("license_number"));
                driver.setId(driverId);
                driver.setPassword(resultSet.getString("password"));
                driver.setLogin(resultSet.getString("login"));
                drivers.add(driver);

            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create new driver", e);
        }
    }

    private void insertCarDrivers(Car car, Connection connection) {
        String query = "INSERT INTO  cars_drivers(driver_id, car_id) VALUES(?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Driver driver: car.getDrivers()) {
                statement.setObject(1, driver.getId());
                statement.setObject(2, car.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add drivers to car " + car, e);
        }
    }

    private void deleteCarDrivers(Long id, Connection connection) {
        String query = "DELETE FROM cars_drivers "
                + "WHERE"
                + "    car_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete all drivers from this car id "
                    + id, e);
        }
    }
}
