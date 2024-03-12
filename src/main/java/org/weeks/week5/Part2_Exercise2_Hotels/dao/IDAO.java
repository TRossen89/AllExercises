package org.weeks.week5.Part2_Exercise2_Hotels.dao;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public interface IDAO <T> {

    List<T> getAll();
    T getById(int id);
    abstract T create(T entity);
    void update(T entity);
    void delete(T entity);
}
