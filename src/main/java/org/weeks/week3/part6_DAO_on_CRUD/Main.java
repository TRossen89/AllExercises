package org.weeks.week3.part6_DAO_on_CRUD;

import jakarta.persistence.EntityManagerFactory;

public class Main {

    static EntityManagerFactory emf;
    public static void main(String[] args) {

        emf = HibernateConfig.getEntityManagerFactoryConfig();
        StudentDAO studentDAO = new StudentDAO(emf);


        Student student1 = new Student("Jonas", "Rossen", 30, "jonasTest@hotmail.com");
        Student student2 = new Student("Finn", "Rossen", 63, "finnTest@hotmail.com");

        studentDAO.createStudent(student1);
        studentDAO.createStudent(student2);

        Student studentRead = studentDAO.readStudent(1);
        System.out.println("Student read: " + studentRead + "\n");

        student1.setFirstName("Tobias");
        studentDAO.updateStudent(1, student1);

        Student studentReadAfterUpdate = studentDAO.readStudent(1);
        System.out.println("Student read: " + studentReadAfterUpdate + "\n");

        studentDAO.deleteStudent(1);

    }
}
