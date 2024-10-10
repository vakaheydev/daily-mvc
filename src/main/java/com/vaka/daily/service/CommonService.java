package com.vaka.daily.service;

import java.util.List;

/**
 * Common service for entity of type {@code T}.
 *
 * @param <T> entity type
 */
public interface CommonService<T> {
    List<T> getAll();

    T getById(Integer id);

    T getByUniqueName(String name);

    T create(T entity);

    T updateById(Integer id, T entity);

    void deleteById(Integer id);

    boolean isServerAlive();
}
