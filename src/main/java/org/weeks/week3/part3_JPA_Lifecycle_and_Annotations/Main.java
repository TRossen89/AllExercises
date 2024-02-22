package org.weeks.week3.part3_JPA_Lifecycle_and_Annotations;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {

    static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args) {

        entityManagerFactory = HibernateConfig.getEntityManagerFactoryConfig();

        // Transient state
        Student student = new Student("Tobias", "Rossen", 34, "tobiasTest@gmail.com");
        // Managed and then detached state
        createStudent(student);


        // Transient state
        Student student1 = new Student("Clarence", "Seedorf", 47, "seedorfTest@hotmail.com");
        // Managed and then detached state
        createStudent(student1);


        // Transient state
        Student student2 = new Student("Nana", "Juhlin", 16, "nanaTest@hotmail.com");
        // Managed and then detached state
        createStudent(student2);


        // Transient state
        Student student3 = new Student("Lykke", "Andersen", 63, "lykkeTesthotmail.com");
        // Managed and then detached state
        createStudent(student3);


        // Transient state
        Student student4 = new Student("Jonas", "Rossen", 30, "jonasTest@homail.com");
        // Managed and then detached state
        createStudent(student4);


        // Managed and then detached state
        updateStudent(1,student1);

        // Managed, detached
        Student studentRead1 = readStudent(1);
        Student studentRead2 = readStudent(2);

        System.out.println("Students read (they are supposed to be the same): \n" + studentRead1 +"\n"+studentRead2);

        // Managed and then removed
        deleteStudent(1);

        // Managed, detached
        List<Student> listOfStudents = readAllStudents();

        System.out.println("\nAll students:");
        
        // Detached/Transient? entities
        listOfStudents.forEach(System.out::println);


    }

    public static void createStudent(Student student){

        try (var em = entityManagerFactory.createEntityManager()) {
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


    public static Student readStudent(int id){


        try (var em = entityManagerFactory.createEntityManager()) {

            // DB, managed, detached?
            return em.find(Student.class, id);
        }
    }


    public static void updateStudent(int id, Student student){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            // Managed and then detached or transient?
            var studentInDB = em.find(Student.class, id);

            //Detached or transient?
            studentInDB.setFirstName(student.getFirstName());
            studentInDB.setLastName(student.getLastName());
            studentInDB.setAge(student.getAge());
            studentInDB.setEmail(student.getEmail());

            // Managed
            em.merge(studentInDB);

            // Detached
            em.getTransaction().commit();
        }
    }

    public static void deleteStudent(int id){

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

    public static List<Student> readAllStudents(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT a FROM Student a", Student.class);

            // Managed and detached
            return query.getResultList();
        }
    }

}
