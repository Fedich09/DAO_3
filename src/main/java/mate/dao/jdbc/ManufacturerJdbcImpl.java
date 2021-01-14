package mate.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.dao.ManufacturerDao;
import mate.exception.DataProcessingException;
import mate.lib.Dao;
import mate.model.Manufacturer;
import mate.util.ConnectionUtil;

@Dao
public class ManufacturerJdbcImpl implements ManufacturerDao {

    private static Optional<Manufacturer> createNewManufacturer(ResultSet resultSet) {
        try {
            Manufacturer manufacturer = new Manufacturer(resultSet.getString("manufacturer_name"),
                    resultSet.getString("manufacturer_country"));
            manufacturer.setId(resultSet.getLong(1));
            return Optional.of(manufacturer);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create new manufacturer", e);
        }
    }

    @Override
    public Manufacturer add(Manufacturer manufacturer) {
        String query = "INSERT INTO taxi_service.manufacturers(manufacturer_name,"
                + " manufacturer_country) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add into DB this manufacture"
                    + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> getById(Long id) {
        String query = "SELECT * FROM taxi_service.manufacturers "
                + "WHERE manufacturer_id = ? "
                + "AND `delete` = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createNewManufacturer(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get element by this id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE taxi_service.manufacturers SET manufacturer_name = ?, "
                + " manufacturer_country = ? WHERE manufacturer_id = ? "
                + "AND `delete` = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            statement.executeUpdate();
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update this manufacturer" + manufacturer, e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE taxi_service.manufacturers SET `delete` = ? "
                + "WHERE manufacturer_id =" + id;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete element by this id" + id, e);
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM taxi_service.manufacturers WHERE `delete` = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                manufacturers.add(createNewManufacturer(resultSet).get());
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all element", e);
        }
    }
}
