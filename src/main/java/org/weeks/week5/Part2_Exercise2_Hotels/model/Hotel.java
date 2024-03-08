package org.weeks.week5.Part2_Exercise2_Hotels.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Generated;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString

public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String hotelName;

    private String address;


    @OneToMany(mappedBy = "hotel", cascade = {CascadeType.PERSIST})
    @ToString.Exclude
    private List<Room> rooms = new ArrayList<>();

    public Hotel(String hotelName, String address) {
        this.hotelName = hotelName;
        this.address = address;
    }

    public void addRoom(Room room){
        if(room != null){
            rooms.add(room);
        }
        room.setHotel(this);
    }
}
