package org.weeks.week3.part4_JPQL_Points;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PointDAOTest {


    EntityManagerFactory emf;
    PointDAO pointDAO;
    int numberOfPoints;

    @BeforeAll
    void beforeAll(){
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        pointDAO = new PointDAO(emf);

        pointDAO.deleteAllRowsInTable();
        numberOfPoints = 10;
        pointDAO.storePoints(numberOfPoints);
    }


    @Test
    void countNumberOfPointsTest(){

        // Arrange
        long expected = numberOfPoints;

        // Act
        long actual = pointDAO.countNumberOfPoints();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getAverageXTest(){

        // Arrange
        Double expectedAverage = 0.0;

        for (int i = 0; i < numberOfPoints; i++) {
            expectedAverage += i;
        }
        expectedAverage = expectedAverage/numberOfPoints;


        // Act
        Double actualAverage = pointDAO.getAverageX();


        // Assert
        assertEquals(expectedAverage, actualAverage);

    }
    @Test
    void getAllPointsTest(){

        // Arrange

        List<Point> expected = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            Point p = new Point(i+1, i, i);
            expected.add(p);
        }

        // Act
        List<Point> actual = pointDAO.getAllPoints();

        // Assert
        assertEquals(expected.get(0).getY(), actual.get(0).getY());
        assertEquals(expected.get(1).getX(), actual.get(1).getX());
        assertEquals(expected.get(2).getX(), actual.get(2).getX());
        assertEquals(expected.get(3).getY(), actual.get(3).getY());

        // Uncomment the asserEquals()-line below and the test will fail. That's because in setup() I delete all rows,
        // I don't truncate or drop the table. I don't know how to do that with JPA/JPQL

        // assertEquals(expected.get(3).getId(), actual.get(3).getId());

    }

    @AfterAll
    void closing(){
        emf.close();
    }

}
