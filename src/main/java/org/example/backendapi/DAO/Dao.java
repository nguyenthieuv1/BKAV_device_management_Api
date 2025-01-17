package org.example.backendapi.DAO;

import java.util.List;
import java.util.Optional;

public interface Dao<T>{
    Optional<T> get(long id);

    Optional<T> get(String keyWord);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);

}
