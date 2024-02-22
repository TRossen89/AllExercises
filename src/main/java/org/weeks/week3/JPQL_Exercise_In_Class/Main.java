package org.weeks.week3.JPQL_Exercise_In_Class;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args) {
        entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();

        EntityManager em = entityManagerFactory.createEntityManager();

        // Getting all employees
        TypedQuery<Employee> query = em.createQuery("SELECT a FROM Employee a", Employee.class);
        List<Employee> listOfEmployees = query.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nAll Employees: ");
        listOfEmployees.forEach(System.out::println);




        // Getting employess with salary above 60000
        TypedQuery<Employee> query1 = em.createQuery("SELECT a FROM Employee a WHERE a.salary >60000", Employee.class);
        List<Employee> listOfEmployeesWithAbove60000 = query1.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nEmployees with 60000+ salary: ");
        listOfEmployeesWithAbove60000.forEach(System.out::println);



        // Getting employees from the Marketing department
        TypedQuery<Employee> query2 = em.createQuery("SELECT a FROM Employee a WHERE a.department = 'Marketing'", Employee.class);
        List<Employee> listOfEmployeesFromMarketing = query2.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nEmployees from Marketing: ");
        listOfEmployeesFromMarketing.forEach(System.out::println);



        // Getting employees who's name starts with J
        TypedQuery<Employee> query3 = em.createQuery("SELECT a FROM Employee a WHERE a.firstName LIKE 'J%'", Employee.class);
        List<Employee> listOfEmployeesStartWithJ = query3.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nEmployees starting with J: ");
        listOfEmployeesStartWithJ.forEach(System.out::println);


        // Me trying something
        TypedQuery<Employee> query4 = em.createQuery("SELECT a FROM Employee a WHERE a.firstName LIKE '%en%'", Employee.class);
        List<Employee> listOfEmployeesWithEN = query4.getResultList();
        System.out.println("\n----------------------------");
        System.out.println("\nEmployees having 'en' in name: ");
        listOfEmployeesWithEN.forEach(System.out::println);


        // Updating employee

        // With named parameters
        em.getTransaction().begin();
        Query query8 = em.createQuery("UPDATE Employee a SET a.salary = :s WHERE a.id = :id").
                setParameter("s", 52000)
                .setParameter("id", 4);
        query8.executeUpdate();
        em.getTransaction().commit();

        // With positional parameters
        em.getTransaction().begin();
        em.createQuery("UPDATE Employee a SET a.salary = ?1 WHERE a.id = ?2").
                setParameter(1, 80000)
                .setParameter(2, 2).executeUpdate();
        em.getTransaction().commit();


        // Getting average salary
        Query query10 = em.createQuery("SELECT avg(a.salary + 0.0) FROM Employee a");
        List<Double> result = query10.getResultList();
        System.out.println("--------------------------\n");
        System.out.println("Average: " + result.get(0));


        // Getting sum of all salaries
        Query query11 = em.createQuery("SELECT sum(a.salary) FROM Employee a");
        List<Integer> resultSum = query11.getResultList();
        System.out.println("--------------------------\n");
        System.out.println("Sum: " + resultSum.get(0));

    }
}
