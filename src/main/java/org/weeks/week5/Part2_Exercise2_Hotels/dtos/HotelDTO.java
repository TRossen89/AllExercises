package org.weeks.week5.Part2_Exercise2_Hotels.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Room;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HotelDTO {

    private Long id;
    private String name;
    private String address;

    @JsonIgnore
    private List<RoomDTO> roomDTOS;

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getHotelName();
        this.address = hotel.getAddress();
    }



    public static Set<HotelDTO> getEntities(List<Hotel> hotels) {
        return hotels.stream().map(hotel -> new HotelDTO(hotel)).collect(Collectors.toSet());
    }
    public static HotelDTO getEntity(Hotel hotel){
        return new HotelDTO(hotel);
    }


}
