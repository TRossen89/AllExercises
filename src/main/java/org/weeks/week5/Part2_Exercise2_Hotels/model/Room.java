package org.weeks.week5.Part2_Exercise2_Hotels.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    private double price;
    @ManyToOne
    private Hotel hotel;

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
    }


}

