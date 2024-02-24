package org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.DAO;

import jakarta.persistence.EntityManagerFactory;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Semester;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Student;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    public Teacher findTeacherWithMostSemesters(){
        List<Teacher> listOfTeachers = new ArrayList<>();
        try (var em = entityManagerFactory.createEntityManager()){

            var query = em.createQuery("SELECT t FROM Semester s JOIN s.teachers t GROUP BY t ORDER BY COUNT(s) DESC",
                    Teacher.class);

            query.setMaxResults(1);
            listOfTeachers = query.getResultList();}


        // This is just me trying to fetch the number of semesters the teacher are teaching
        try (var em = entityManagerFactory.createEntityManager()){
            if (!listOfTeachers.isEmpty()){
                Teacher teacher = listOfTeachers.get(0);
                var query2 = em.createQuery("SELECT s FROM Semester s JOIN s.teachers t WHERE t.id = ?1")
                        .setParameter(1,teacher.getId());

                List<Semester> semesters = query2.getResultList();
                Set<Semester> semesterSet = semesters.stream().collect(Collectors.toSet());
                teacher.setSemesters(semesterSet);

                return teacher;
            }
            else {
                return null;
            }

            //return query.getResultList();
            //return listOfTeachers.isEmpty() ? null : listOfTeachers.get(0);
        }

    };


}
