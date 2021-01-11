package mate.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T add(T object);

    Optional<T> getById(Long id);

    T update(Long id, T object);

    boolean deleteById(Long id);

    boolean delete(T object);

    List<T> getAll();
}
