package org.weeks.week5.part2_Vetirinarian_exercise.controllers;


import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.weeks.week5.part2_Vetirinarian_exercise.model.Appointment;
import org.weeks.week5.part2_Vetirinarian_exercise.model.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentController {

    private static final Map<Integer, Appointment> appointmentMap = new HashMap<>();

    public static void addAppointments(){

        LocalDate localDate1 = LocalDate.of(2024, 3, 23);
        LocalDate localDate2 = LocalDate.of(2024, 1, 15);
        LocalDate localDate3 = LocalDate.of(2024, 1, 20);
        LocalDate localDate4 = LocalDate.of(2024, 2, 8);
        LocalDate localDate5 = LocalDate.of(2024, 4, 8);
        LocalTime localTime = LocalTime.of(15, 00);

        Patient patient1 = PatientController.getPatientFromMapWithId(1);
        Patient patient2 = PatientController.getPatientFromMapWithId(2);
        Patient patient3 = PatientController.getPatientFromMapWithId(3);
        Patient patient4 = PatientController.getPatientFromMapWithId(4);
        Patient patient5 = PatientController.getPatientFromMapWithId(5);

        Appointment appointment1 = new Appointment(localDate1,localTime, patient1, 60, "Freud");
        Appointment appointment2 = new Appointment(localDate2,localTime, patient2, 60, "Freud");
        Appointment appointment3 = new Appointment(localDate3,localTime, patient3, 60, "Freud");
        Appointment appointment4 = new Appointment(localDate4,localTime, patient4, 60, "Freud");
        Appointment appointment5 = new Appointment(localDate5,localTime, patient5, 60, "Freud");

        appointmentMap.put(1, appointment1);
        appointmentMap.put(2, appointment2);
        appointmentMap.put(3, appointment3);
        appointmentMap.put(4, appointment4);
        appointmentMap.put(5, appointment5);

    }
    public Handler getUpcomingAppointments() {

        LocalDate dateOfToday = LocalDate.now();

        Map<Integer, Appointment> upcommingAppointments = appointmentMap.entrySet().stream()
                .filter(entrySet -> entrySet.getValue().getDateOfAppointment().isAfter(dateOfToday))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return ctx -> ctx.status(200).json(upcommingAppointments.values());


    }

    public Handler getById() {

        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Appointment appointment = appointmentMap.get(id);
            if(appointment!= null){
                ctx.json(appointment);
            }
            else{
                ctx.status(404);
                ctx.attribute("message", "No appointment with id: " + id);
            }
        };
    }
}
