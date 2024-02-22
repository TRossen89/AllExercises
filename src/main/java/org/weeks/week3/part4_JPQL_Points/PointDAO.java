package org.weeks.week3.part4_JPQL_Points;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PointDAO {

    private EntityManagerFactory entityManagerFactory;

    public PointDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void storePoints(int numberOfPoints) {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            for (int i = 0; i < numberOfPoints; i++) {
                Point p = new Point(i, i);
                em.persist(p);
            }
            em.getTransaction().commit();
        }
    }

    public Long countNumberOfPoints() {

        try (var em = entityManagerFactory.createEntityManager()) {
            Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");

            List<Long> numberOfPoints = q1.getResultList();

            return numberOfPoints.get(0);
        }
    }

    public Double getAverageX() {

        try (var em = entityManagerFactory.createEntityManager()) {

            Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");

            List<Double> average = q2.getResultList();

            return average.get(0);
        }
    }

    public List<Point> getAllPoints() {
        try (var em = entityManagerFactory.createEntityManager()) {

            TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
            List<Point> results = query.getResultList();

            return results;
        }
    }

    public void deleteAllRowsInTable() {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Point").executeUpdate();
            em.getTransaction().commit();
        }
    }
}
