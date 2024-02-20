package org.weeks.week4.part0_Dolphin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class NoteDTO {

    String name;
    int age;
    String note;

    public NoteDTO(String name, int age, String note) {
        this.name = name;
        this.age = age;
        this.note = note;
    }


}
