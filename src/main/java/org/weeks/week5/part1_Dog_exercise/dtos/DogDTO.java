package org.weeks.week5.part1_Dog_exercise.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DogDTO {

    private int id;
    private String name;
    private String breed;
    private String gender;
    private int age;
}
