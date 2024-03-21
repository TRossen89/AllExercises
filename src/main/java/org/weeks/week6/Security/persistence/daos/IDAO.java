package org.weeks.week6.Security.persistence.daos;


import java.util.List;

public interface IDAO <T> {

    List<T> getAll();
    T getById(Long id);
    abstract T create(T entity);
    T update(T entity);
    void delete(T entity);
}