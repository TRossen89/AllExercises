package org.weeks.week3.JPQL_Exercise_In_Class;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "employee")
@NoArgsConstructor
/*
@NamedQueries({
        @NamedQuery()
})

 */
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "salary")
    Double salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    Department department;

    public Employee(String firstName, String lastName, String email, Double salary, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "\n\nEmployee:\n" +
                "Id: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nEmail: " + email +
                "\nSalary: " + salary +
                "\nDepartment: " + department;
    }
}
