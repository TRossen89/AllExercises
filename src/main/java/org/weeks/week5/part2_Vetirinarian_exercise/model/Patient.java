package org.weeks.week5.part2_Vetirinarian_exercise.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@ToString
public class Patient {

    private static int idCounter = 0;

    private int id;
    private String nameOfOwner;

    private String animal;

    private int phoneNumberOfOwner;

    private List<String> deceases;



    public Patient(String nameOfOwner, int phoneNumberOfOwner, String animal, List<String> deceases) {
        this.nameOfOwner = nameOfOwner;
        this.phoneNumberOfOwner = phoneNumberOfOwner;
        this.animal = animal;
        this.deceases = deceases;
        idCounter++;
        id = idCounter;

    }

    public void addDeceaseToMedicalHistory(String decease){
        deceases.add(decease);
    }

}
