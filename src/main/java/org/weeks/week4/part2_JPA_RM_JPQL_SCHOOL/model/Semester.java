package org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.model;

import org.weeks.week4.part2_JPA_RM_JPQL_SCHOOL.config.HibernateConfig;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "semester")
@ToString
@NoArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "semester")
    private Set<Student> students = new HashSet<>();

    @ToString.Exclude
    @ManyToMany
    private Set<Teacher> teachers = new HashSet<>();


    public Semester(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
        /*if(!teachers.isEmpty()){
        }
         */
    }

    public void addStudent(Student student){
        this.students.add(student);
        if(student!=null){
            student.addSemester(this);
        }
    }
}