package org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.DAO;

import jakarta.persistence.EntityManagerFactory;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Student;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Teacher;

import java.util.List;

public class StudentDAO implements IStudentDAO{


    EntityManagerFactory entityManagerFactory;

    public StudentDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Student> findAllStudentsByFirstName(String firstName){

        try (var em = entityManagerFactory.createEntityManager()){

            var query = em.createQuery("SELECT a FROM Student a WHERE a.firstName = :firstName", Student.class)
                    .setParameter("firstName", firstName);

            return query.getResultList();
        }

    };

    public List<Student> findAllStudentsByLastName(String lastName){

        try (var em = entityManagerFactory.createEntityManager()){

            var query = em.createQuery("SELECT a FROM Student a WHERE a.lastName = :lastName", Student.class)
                    .setParameter("lastName", lastName);

            return query.getResultList();
        }
    };


    public long findTotalNumberOfStudentsBySemester(String semesterName){

        try (var em = entityManagerFactory.createEntityManager()){

            var query = em.createQuery("SELECT COUNT(s) FROM Semester a JOIN a.students s WHERE a.name = :name")
                    .setParameter("name", semesterName);

            return (long) query.getSingleResult();
        }
    }


    public long findTotalNumberOfStudentsByTeacher(Teacher teacher){

        try (var em = entityManagerFactory.createEntityManager()){

            var query = em.createQuery("SELECT COUNT(s) FROM Semester a JOIN a.students s " +
                    "JOIN a.teachers t WHERE t.id = ?1").setParameter(1, teacher.getId());

            //return query.getResultList();
            return (long) query.getSingleResult();
        }

    }



}
