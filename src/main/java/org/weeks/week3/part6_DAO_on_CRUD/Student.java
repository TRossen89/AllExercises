package org.weeks.week3.part6_DAO_on_CRUD;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString

@Entity
@Table(name = "student_new")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_mame", nullable = false)
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "email", unique = true)
    private String email;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timeStamp;

    public Student(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    @PreUpdate
    public void timeStampUpdated() throws RuntimeException {

        LocalDateTime localDateTime = LocalDateTime.now();

        this.timeStamp = localDateTime;
    }

    @PrePersist
    public void timeStamp() throws RuntimeException {

        LocalDateTime localDateTime = LocalDateTime.now();

        this.timeStamp = localDateTime;

    }
}
