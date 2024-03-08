package org.weeks.week5.part2_Vetirinarian_exercise.dtos;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString

public class AppointmentDTO {

    private int id;

    private static int idCounter = 0;
    private LocalDate dateOfAppointment;
    private LocalTime timeOfAppointment;
    private PatientDTO patientDTO;

    private int durationInMinutes;

    private String vetirinarian;

    public AppointmentDTO(LocalDate dateOfAppointment, LocalTime timeOfAppointment, PatientDTO patientDTO, int durationInMinutes, String vetirinarian) {
        this.dateOfAppointment = dateOfAppointment;
        this.timeOfAppointment = timeOfAppointment;
        this.patientDTO = patientDTO;
        this.durationInMinutes = durationInMinutes;
        this.vetirinarian = vetirinarian;
        idCounter++;
        this.id = idCounter;
    }
}
