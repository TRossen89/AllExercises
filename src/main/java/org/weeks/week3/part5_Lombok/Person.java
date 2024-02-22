package org.weeks.week3.part5_Lombok;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    private String firstName;
    private String lastName;
    private int age;

}
