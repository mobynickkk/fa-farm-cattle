package ru.fa.service.domain;

import java.util.Collection;

public interface CrudService<T> {
    T createOrUpdate(T dto);

    Collection<T> getAll(String username);

    T getById(String username, String id);

    void slaughter(String username, String id);
}
