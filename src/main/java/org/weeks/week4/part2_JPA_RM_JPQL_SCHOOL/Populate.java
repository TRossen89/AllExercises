package org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.config.HibernateConfig;

public class Populate {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("week4", false);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();



            // populate the database with data
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
