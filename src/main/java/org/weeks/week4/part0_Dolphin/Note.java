package org.weeks.week4.part0_Dolphin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    private String note;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timeStamp;

    @ManyToOne
    @ToString.Exclude
    private Person createdBy;
    @ManyToOne
    @ToString.Exclude
    private Person person;

    public Note(String note, Person createdBy) {
        this.note = note;
        this.createdBy = createdBy;

    }

    @PrePersist
    public void timeStamp () throws RuntimeException {
        LocalDateTime localDateTime = java.time.LocalDateTime.now();
        this.timeStamp = localDateTime;

    }


}
