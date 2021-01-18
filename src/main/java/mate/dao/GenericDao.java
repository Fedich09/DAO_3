package mate.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, I> {
    T create(T object);

    Optional<T> get(I id);

    List<T> getAll();

    T update(T object);

    boolean delete(I id);
}
