package mate.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    T add(T object);

    Optional<T> getById(Long t);

    T update(Long id, T object);

    boolean deleteById(Long id);

    boolean delete(T object);

    List<T> getAll();
}
