package org.weeks.week3.part1_and_part2_Basic_JPA_CRUD;

import jakarta.persistence.EntityManagerFactory;

public class Main {

    static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args) {
        entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();
        UnicornDAO unicornDAO = new UnicornDAO(entityManagerFactory);

        Unicorn unicorn1 = new Unicorn("Jonas", 30, 72);
        unicornDAO.save(unicorn1);

        Unicorn unicorn2 = new Unicorn("Tobias", 34, 0);
        unicornDAO.save(unicorn2);

        Unicorn unicorn3 = new Unicorn("Finn", 63, 56);
        unicornDAO.save(unicorn3);

        unicorn2.setId(2);
        unicorn2.setName("SuperTobias");
        unicornDAO.update(unicorn2);


        Unicorn unicorn3FromDB = unicornDAO.findById(3);
        System.out.println("Unicorn from DB: " + unicorn3FromDB.getName());

        unicornDAO.delete(1);
    }
}