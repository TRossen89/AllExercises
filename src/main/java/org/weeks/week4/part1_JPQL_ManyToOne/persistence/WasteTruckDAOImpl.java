package org.weeks.week4.part1_JPQL_ManyToOne.persistence;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.weeks.week4.part1_JPQL_ManyToOne.model.Driver;
import org.weeks.week4.part1_JPQL_ManyToOne.model.WasteTruck;

import java.util.List;


public class WasteTruckDAOImpl {

    EntityManagerFactory entityManagerFactory;

    public WasteTruckDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setFirstIdInTable() {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("ALTER SEQUENCE waste_truck_id_seq RESTART WITH 8").executeUpdate();
            em.getTransaction().commit();
        }
    }

    public void saveWasteTruck(WasteTruck wasteTruck) {

        try (var em = entityManagerFactory.createEntityManager()) {

            em.getTransaction().begin();
            em.persist(wasteTruck);
            em.getTransaction().commit();
        }

    }

    ;

    public WasteTruck getWasteTruckById(int id) {

        try (var em = entityManagerFactory.createEntityManager()) {
            return em.find(WasteTruck.class, id);
        }
    }

    public void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available) {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            WasteTruck wasteTruckFromDB = em.find(WasteTruck.class, wasteTruck.getId());

            wasteTruckFromDB.setIs_available(available);

            em.merge(wasteTruckFromDB);

            em.getTransaction().commit();
        }
    }

    public void deleteWasteTruck(WasteTruck wastetruck) {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            // DB and then managed
            //var wastetruck = em.find(WasteTruck.class, id);

            // Managed
            em.remove(wastetruck);

            // Removed
            em.getTransaction().commit();
        }
    }

    ;

    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver) {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            Driver driverFromDB = em.find(Driver.class, driver.getId());

            driverFromDB.setWaste_truck(wasteTruck);

            em.merge(driverFromDB);

            em.getTransaction().commit();
        }
    }

    ;

    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id) {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            Driver driverFromDB = em.find(Driver.class, id);

            wasteTruck.removeDriver(driverFromDB);
            driverFromDB.setWaste_truck(null);

            em.merge(driverFromDB);

            em.getTransaction().commit();
        }
    }

    public List<WasteTruck> getAllAvailableTrucks() {

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            Query query = em.createQuery("SELECT a FROM WasteTruck a WHERE a.is_available = true", WasteTruck.class);

            // Managed and detached
            return query.getResultList();
        }

    }
}
