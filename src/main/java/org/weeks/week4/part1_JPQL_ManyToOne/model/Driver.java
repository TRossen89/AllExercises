package org.weeks.week4.part1_JPQL_ManyToOne.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import lombok.ToString;
import org.weeks.week4.part1_JPQL_ManyToOne.config.HibernateConfig;
@ToString
@Getter
@Setter
@Entity
@Table(name = "driver")
@NoArgsConstructor
public class Driver {
    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal salary;
    private String surname;
/*
    @ManyToOne()
    private WasteTruck wasteTruck;

 */

    public Driver(String name, String surname, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;

    }


    @PrePersist
    public void generateDateAndDriverId(){

        this.employmentDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");


        String dateOfEmployment = formatter.format(employmentDate).replaceAll("-", "");


        Character nameFirstLetter = this.name.charAt(0);
        Character surnameFirstLetter = this.surname.charAt(0);
        Character surnameLastLetter = this.surname.toUpperCase().charAt(this.surname.length()-1);
        Random random = new Random();

        Integer randomNumber = random.nextInt(100, 200);

        System.out.println("Formatted date: " + dateOfEmployment);
        System.out.println("Name first letter: " + nameFirstLetter);
        System.out.println("Surname first letter: " + surnameFirstLetter);
        System.out.println("Surname last letter: " + surnameLastLetter);
        System.out.println("Random number: " + randomNumber);

        this.id = dateOfEmployment + "-" + nameFirstLetter + surnameFirstLetter + "-"
                + randomNumber + surnameLastLetter;

        System.out.println("Id: " + this.id);

        boolean idValidated = validateDriverId(this.id);

        if(idValidated){
            System.out.println("Id validated");
        }
        else {
            throw new RuntimeException("Id not validated");
        }

    }

    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }
}