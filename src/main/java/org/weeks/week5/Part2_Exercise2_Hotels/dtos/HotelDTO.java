package org.weeks.week5.Part2_Exercise2_Hotels.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Room;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HotelDTO {

    private int id;
    private String name;
    private String address;

    private List<RoomDTO> roomDTOS;


}
