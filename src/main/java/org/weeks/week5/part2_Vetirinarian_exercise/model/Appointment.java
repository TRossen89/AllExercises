package org.weeks.week5.part2_Vetirinarian_exercise.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString

public class Appointment {

    private int id;

    private static int idCounter = 0;
    private LocalDate dateOfAppointment;
    private LocalTime timeOfAppointment;
    private Patient patient;

    private int durationInMinutes;

    private String vetirinarian;

    public Appointment(LocalDate dateOfAppointment, LocalTime timeOfAppointment, Patient patient, int durationInMinutes, String vetirinarian) {
        this.dateOfAppointment = dateOfAppointment;
        this.timeOfAppointment = timeOfAppointment;
        this.patient = patient;
        this.durationInMinutes = durationInMinutes;
        this.vetirinarian = vetirinarian;
        idCounter++;
        this.id = idCounter;
    }
}
