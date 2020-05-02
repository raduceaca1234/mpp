package movieRental.core.service;

import movieRental.core.model.Entity;

import java.io.Serializable;
import java.util.List;

public interface BaseServiceInterface<T extends Entity<ID>, ID extends Serializable> extends Serializable {
    List<T> getAll();

    T save(T entity);

    Boolean deleteById(ID id);

    T update(ID id, T entity);
}
