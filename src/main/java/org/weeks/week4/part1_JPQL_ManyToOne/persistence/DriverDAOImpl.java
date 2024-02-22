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
            var query = em.createQuery("SELECT a FROM Driver a WHERE year (a.employmentDate) = :year", Driver.class)
                    .setParameter("year", year);

            // Managed and detached
            return query.getResultList();
        }
    }


    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a FROM Driver a WHERE a.salary > ?1", Driver.class)
                    .setParameter(1, 10000);

            // Managed and detached
            return query.getResultList();
        }
    }

    public double fetchHighestSalary(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT max(a.salary) FROM Driver a");

            // Managed and detached
            return Double.parseDouble(query.getSingleResult().toString());
        }

    }


    public List<String> fetchFirstNameOfAllDrivers(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a.name FROM Driver a");

            // Managed and detached
            return query.getResultList();
        }


    };

    public long calculateNumberOfDrivers(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT COUNT (a) FROM Driver a ");

            // Managed and detached
            return (long) query.getSingleResult();
        }

    };


    public Driver fetchDriverWithHighestSalary(){
        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a FROM Driver a ORDER BY a.salary DESC", Driver.class);
            query.setMaxResults(1);
            List<Driver> resultList = query.getResultList();
            // Managed and detached
            return resultList.isEmpty() ? null : resultList.get(0);
        }


    };


};



