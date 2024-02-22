package org.weeks.week3.part4_JPQL_Points;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {

    static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args) {

        entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();

        PointDAO pointDAO = new PointDAO(entityManagerFactory);

        pointDAO.deleteAllRowsInTable();

        // Store 1000 Point objects in the database:
        pointDAO.storePoints(1000);




        // Find the number of Point objects in the database:
        Long numberOfPoints = pointDAO.countNumberOfPoints();
        System.out.println("\nNumber of points: " + numberOfPoints);


        // Find the average X value:
        Double averageX = pointDAO.getAverageX();
        System.out.println("\nAverage X: " + averageX);

        // Retrieve all the Point objects from the database:
        List<Point> listOfPoints = pointDAO.getAllPoints();
        listOfPoints.forEach(System.out::println);

    }
}
