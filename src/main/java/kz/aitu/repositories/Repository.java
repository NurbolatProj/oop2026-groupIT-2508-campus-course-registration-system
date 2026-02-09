package kz.aitu.repositories;

import java.util.List;

public interface Repository<T> {  // Generics
    boolean create(T entity);
    List<T> findAll();
}