package org.weeks.week3.part6_DAO_on_CRUD;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class StudentDAO {

    EntityManagerFactory entityManagerFactory;

    public StudentDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void createStudent(Student student){

        try (var em = this.entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            try{
                // Managed state
                em.persist(student);
            }catch (RuntimeException e){
                System.out.println(e);
            }
            // Detached state
            em.getTransaction().commit();
        }
    }


    public Student readStudent(int id){
        try (var em = entityManagerFactory.createEntityManager()) {

            // DB, managed, detached?
            return em.find(Student.class, id);
        }
    }


    public void updateStudent(int id, Student student){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            // DB, managed and then transient?
            var studentInDB = em.find(Student.class, id);

            //Transient?
            studentInDB.setFirstName(student.getFirstName());
            studentInDB.setLastName(student.getLastName());
            studentInDB.setAge(student.getAge());
            studentInDB.setEmail(student.getEmail());

            // Managed, DB
            em.merge(studentInDB);

            // Detached
            em.getTransaction().commit();
        }
    }

    public void deleteStudent(int id){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            // DB and then managed
            var student = em.find(Student.class, id);

            // Managed
            em.remove(student);

            // Removed
            em.getTransaction().commit();
        }
    }

    public List<Student> readAllStudents(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a FROM Student a", Student.class);

            // Managed and detached
            return query.getResultList();
        }
    }

}
