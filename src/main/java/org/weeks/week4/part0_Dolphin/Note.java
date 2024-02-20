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
    int id;

    @Column(name = "note")
    String note;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime timeStamp;

    @Column(name = "created_by")
    String createdBy;

}
