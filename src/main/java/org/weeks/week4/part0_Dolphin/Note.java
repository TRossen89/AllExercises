package org.weeks.week4.part0_Dolphin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "note")
    private String note;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timeStamp;

    @ManyToOne
    @ToString.Exclude
    private Person createdBy;

    public Note(String note, LocalDateTime timeStamp, Person createdBy) {
        this.note = note;
        this.timeStamp = timeStamp;
        this.createdBy = createdBy;
    }
}
