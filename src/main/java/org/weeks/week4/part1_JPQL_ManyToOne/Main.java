package org.weeks.week4.part1_JPQL_ManyToOne;

import jakarta.persistence.EntityManagerFactory;

import org.weeks.week4.part1_JPQL_ManyToOne.config.HibernateConfig;
import org.weeks.week4.part1_JPQL_ManyToOne.model.Driver;
import org.weeks.week4.part1_JPQL_ManyToOne.model.WasteTruck;
import org.weeks.week4.part1_JPQL_ManyToOne.persistence.DriverDAOImpl;
import org.weeks.week4.part1_JPQL_ManyToOne.persistence.IWasteTruckDAO;
import org.weeks.week4.part1_JPQL_ManyToOne.persistence.WasteTruckDAOImpl;

import java.math.BigDecimal;
import java.util.List;

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

        WasteTruck wasteTruck1 = new WasteTruck("Volvo", 40000, "A211-55-H5");


        WasteTruckDAOImpl wasteTruckDAO = new WasteTruckDAOImpl(emf);

        //Setting start of id counting so that I can run the program again without changing in the DB
        //TODO Native query
        wasteTruckDAO.setFirstIdInTable();

        wasteTruckDAO.saveWasteTruck(wasteTruck1);
        wasteTruckDAO.setWasteTruckAvailable(wasteTruck1,true);


        System.out.println(driverDAO.getDriverById(driver1.getId()));

        // Getting drivers by year
        System.out.println("-----------------------\nDrivers by year: \n");
        List<Driver> driverListYear2024 = driverDAO.findAllDriversEmployedAtTheSameYear("2024");
        driverListYear2024.forEach(driver -> System.out.println("Driver: " + driver.getName() +"\n" +
                "Year: " + driver.getEmploymentDate()+"\n"));


        // Getting drivers by salart greater than 1000
        System.out.println("-----------------------\nDrivers with salary greater than 10000: \n");

        //TODO TypedQuery
        List<Driver> driverListSalaryGreaterThan1000 = driverDAO.fetchAllDriversWithSalaryGreaterThan10000();

        driverListSalaryGreaterThan1000.forEach(driver -> {
            System.out.println("Driver: " + driver.getName() +"\n" + "Salary: " + driver.getSalary()+"\n");
        });

        // Getting highest salary
        System.out.println("-----------------------\nHighest salary: \n");

        //TODO Named query
        Double highestSalary = driverDAO.fetchHighestSalary();
        System.out.println(highestSalary);


        // Getting all driver's first name
        System.out.println("-----------------------\nFirst name of drivers: \n");
        List<String> allDriversFirstname = driverDAO.fetchFirstNameOfAllDrivers();
        allDriversFirstname.forEach(System.out::println);

        // Counting drivers
        System.out.println("-----------------------\nNumber of drivers: \n");
        System.out.println(driverDAO.calculateNumberOfDrivers());

        // Getting driver with highest salary
        System.out.println("-----------------------\nDriver with highest salary: \n");
        System.out.println(driverDAO.fetchDriverWithHighestSalary());


        // Getting waste truck
        System.out.println(wasteTruckDAO.getWasteTruckById(wasteTruck1.getId()));

        // Adding driver to wastetruck
        System.out.println("-----------------------\nAdding driver to wastetruck... \n");
        wasteTruckDAO.addDriverToWasteTruck(wasteTruck1, driver3);
        wasteTruckDAO.addDriverToWasteTruck(wasteTruck1, driver1);

        System.out.println(driverDAO.getDriverById(driver3.getId()));
        System.out.println(driverDAO.getDriverById(driver1.getId()));

        System.out.println("-----------------------\nRemoving driver from wastetruck... \n");
        wasteTruckDAO.removeDriverFromWasteTruck(wasteTruck1, driver3.getId());

        System.out.println(driverDAO.getDriverById(driver3.getId()));
        System.out.println(driverDAO.getDriverById(driver1.getId()));

        System.out.println("-----------------------\nGetting all available wastetrucks: \n");
        List<WasteTruck> allAvailableWasteTrucks = wasteTruckDAO.getAllAvailableTrucks();

        allAvailableWasteTrucks.forEach(System.out::println);


        // Deleting driver and wastetruck so that I can run the program again without changing in the DB
        driverDAO.deleteDriver(driver1.getId());
        wasteTruckDAO.deleteWasteTruck(wasteTruck1);

    }
}
