package mate.service;

import java.util.List;

public interface Service<T> {
    T add(T object);

    T getById(Long t);

    T update(T object);

    boolean deleteById(Long id);

    boolean delete(T object);

    List<T> getAll();
}
