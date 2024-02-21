package org.weeks.week4.part1_JPQL_ManyToOne.persistence;


import org.weeks.week4.part1_JPQL_ManyToOne.model.Driver;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManagerFactory;


public class DriverDAOImpl implements IDriverDAO {

    EntityManagerFactory entityManagerFactory;


    public DriverDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public void saveDriver(Driver driver) {

        try (var em = entityManagerFactory.createEntityManager()) {

            em.getTransaction().begin();
            try {
                em.persist(driver);
            } catch (RuntimeException msg) {
                System.out.println(msg);
            }
            em.getTransaction().commit();
        }

    }

    public Driver getDriverById(String id) {

        try (var em = entityManagerFactory.createEntityManager()) {
            return em.find(Driver.class, id);
        }
    }


    public Driver updateDriver(Driver driver) {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            Driver driverFromDB = em.find(Driver.class, driver.getId());

            driverFromDB.setSurname(driver.getSurname());
            driverFromDB.setName(driver.getName());
            driverFromDB.setSalary(driver.getSalary());

            em.merge(driverFromDB);

            em.getTransaction().commit();

            return driverFromDB;
        }
    }

    public void deleteDriver(String id) {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            // DB and then managed
            var driver = em.find(Driver.class, id);

            // Managed
            em.remove(driver);

            // Removed
            em.getTransaction().commit();
        }
    }


    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a FROM Driver a WHERE year (a.employmentDate) = :date", Driver.class)
                    .setParameter("date", year);

            // Managed and detached
            return query.getResultList();
        }
    }


    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a FROM Driver a WHERE a.salary > ?", Driver.class)
                    .setParameter(1, 1000);

            // Managed and detached
            return query.getResultList();
        }
    }

    public double fetchHighestSalary(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a.salary FROM Driver a WHERE a.salary = max()")
                    .setParameter(1, 1000);

            // Managed and detached
            return Double.parseDouble(query.getSingleResult().toString());
        }

    }
};
/*
    List<String> fetchFirstNameOfAllDrivers();

    long calculateNumberOfDrivers();

    Driver fetchDriverWithHighestSalary();

 */


