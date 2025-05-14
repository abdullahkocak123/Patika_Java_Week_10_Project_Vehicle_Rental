package vehicle_rental.dao;

import java.util.List;

public interface BaseDAO <T> {

    void save(T t);

    T findById(Long id);

    List <T> findAll();

    void update (T t);

    void delete (Long id);
}
