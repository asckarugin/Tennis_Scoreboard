package by.asckarugin.Repositories;

import java.util.List;
import java.util.Optional;

public interface RepositoryCRUD<K,E> {
    List<E> findAll();

    Optional<E> findById(K id);

    E save(E entity);

    void update(E entity);

    void delete(K id);
}
