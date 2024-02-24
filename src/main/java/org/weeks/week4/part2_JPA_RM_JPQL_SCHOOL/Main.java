package org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.DAO.StudentDAO;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.config.HibernateConfig;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Semester;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Student;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.StudentInfo;
import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model.Teacher;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("week4", false);

        StudentDAO studentDAO = new StudentDAO(emf);

        // Getting all students by first name
        List<Student> listOfStudents = studentDAO.findAllStudentsByFirstName("Tobias");
        System.out.println("\n\n----------------------------------\n");
        listOfStudents.forEach(System.out::println);


        List<Student> listOfStudentsByLastName = studentDAO.findAllStudentsByLastName("Rossen");
        System.out.println("\n\n----------------------------------\n");
        listOfStudentsByLastName.forEach(System.out::println);


        // Finding number of students in a semester
        long numberOfStudentsInPhilosophy = studentDAO.findTotalNumberOfStudentsBySemester("Philosophy");
        System.out.println("-----------------------\nNumber of students in Philosophy: \n");
        System.out.println(numberOfStudentsInPhilosophy);


        // Finding students by teacher
        Teacher teacher1 = new Teacher("Jerry", "Fodor", 1);

        System.out.println("-----------------------\nNumber of students having teacher1 (Klaus Holzkamp): \n");
        System.out.println(studentDAO.findTotalNumberOfStudentsByTeacher(teacher1));


        // Finding teacher with most semesters

        Teacher teacherWithMostSemesters = studentDAO.findTeacherWithMostSemesters();
        System.out.println("-----------------------\nTeacher with most semesters: \n");
        System.out.println(teacherWithMostSemesters);
        System.out.println("Number of semesters: " + teacherWithMostSemesters.getSemesters().size());


        // Finding semester with fewest students
        System.out.println("-----------------------\nSemester with fewest students: \n");
        Semester semesterWithFewestStudents = studentDAO.findSemesterWithFewestStudents();
        System.out.println(semesterWithFewestStudents.getName());


        // List with student's student info
        List<StudentInfo> listOfStudentInfo = studentDAO.getAllStudentInfo();

        System.out.println("-----------------------\nStudent info of all students: \n");
        listOfStudentInfo.forEach(System.out::println);

        System.out.println("-----------------------\nStudent info of one student: \n");
        System.out.println(studentDAO.getAllStudentInfo(2));

    }

}
