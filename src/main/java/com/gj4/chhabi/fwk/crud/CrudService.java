package com.gj4.chhabi.fwk.crud;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
public interface CrudService<T> {

    T create(T object);

    T update(T object);

    T find(String id);

    void delete(String id);
}
