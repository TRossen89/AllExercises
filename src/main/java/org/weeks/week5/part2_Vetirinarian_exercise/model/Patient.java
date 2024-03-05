package org.weeks.week5.part2_Vetirinarian_exercise.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@AllArgsConstructor
@ToString
public class Patient {

    private static int idCounter = 0;

    private int id;
    private String name;
    private int phoneNumber;



    public Patient(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        idCounter++;
        id = idCounter;

    }

}
