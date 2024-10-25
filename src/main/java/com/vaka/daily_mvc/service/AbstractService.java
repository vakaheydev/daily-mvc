package com.vaka.daily_mvc.service;

import com.vaka.daily_client.client.Client;

import java.util.List;

/**
 * Abstract service with common CRUD functionality for entity of type {@code T}.
 *
 * @param <T> entity type
 */
public abstract class AbstractService<T> implements CommonService<T> {
    @Override
    public List<T> getAll() {
        return getClient().getAll();
    }

    @Override
    public T getById(Integer id) {
        return getClient().getById(id);
    }

    @Override
    public T getByUniqueName(String name) {
        return getClient().getByUniqueName(name);
    }

    @Override
    public T create(T entity) {
        return getClient().create(entity);
    }

    @Override
    public T updateById(Integer id, T entity) {
        return getClient().updateById(id, entity);
    }

    @Override
    public void deleteById(Integer id) {
        getClient().deleteById(id);
    }

    @Override
    public boolean isServerAlive() {
        return getClient().isServerAlive();
    }

    public abstract Client<T> getClient();
}
