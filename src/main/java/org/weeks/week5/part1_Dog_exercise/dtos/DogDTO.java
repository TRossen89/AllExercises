package org.weeks.week5.part1_Dog_exercise.dtos;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DogDTO {

    private int id;
    private String name;
    private String breed;
    private String gender;
    private int age;
}
