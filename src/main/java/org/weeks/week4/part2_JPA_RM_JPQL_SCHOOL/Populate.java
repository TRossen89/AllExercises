package org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.config.HibernateConfig;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Semester;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Student;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Teacher;

public class Populate {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("week4", false);
        EntityManager em = emf.createEntityManager();

        Student student1 = new Student("Tobias", "Rossen");
        Student student2 = new Student("Jonas", "Rossen");
        Student student3 = new Student("Finn", "Rossen");
        Student student4 = new Student("Lykke", "Andersen");
        Student student5 = new Student("Angela", "Juhlin");

        Teacher teacher1 = new Teacher("KLaus", "Holzkamp");
        Teacher teacher2 = new Teacher("David", "Hume");
        Teacher teacher3 = new Teacher("Patrick", "Blackburn");
        Teacher teacher4 = new Teacher("Maja", "Plum");
        Teacher teacher5 = new Teacher("Jerry", "Fodor");

        Semester semester1 = new Semester("Philosophy", "Epistemology");
        Semester semester2 = new Semester("Educational Studies", "Situated Learning");
        Semester semester3 = new Semester("Psychology", "Critical Psychology");



        try {
            em.getTransaction().begin();

            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);
            em.persist(student5);

            em.persist(teacher1);
            em.persist(teacher2);
            em.persist(teacher3);
            em.persist(teacher4);
            em.persist(teacher5);

            em.persist(semester1);
            em.persist(semester2);
            em.persist(semester3);


            // populate the database with data
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
