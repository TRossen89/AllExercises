package org.weeks.week4.part1_JPQL_ManyToOne;

import jakarta.persistence.EntityManagerFactory;

import org.weeks.week4.part1_JPQL_ManyToOne.config.HibernateConfig;
import org.weeks.week4.part1_JPQL_ManyToOne.model.Driver;
import org.weeks.week4.part1_JPQL_ManyToOne.persistence.DriverDAOImpl;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("week4", false);

        DriverDAOImpl driverDAO = new DriverDAOImpl(emf);

        Driver driver1 = new Driver("Tobias", "Rossen", new BigDecimal(10000));
        Driver driver2 = new Driver("Jonas", "Rossen", new BigDecimal(20000));
        Driver driver3 = new Driver("Finn", "Rossen", new BigDecimal(30000));

        driverDAO.saveDriver(driver1);
        driverDAO.saveDriver(driver2);
        driverDAO.saveDriver(driver3);

        System.out.println(driverDAO.getDriverById(driver1.getId()));

    }
}
