package org.weeks.week5.part2_Vetirinarian_exercise.controllers;


import io.javalin.http.Handler;
import org.weeks.week5.part2_Vetirinarian_exercise.dtos.AppointmentDTO;
import org.weeks.week5.part2_Vetirinarian_exercise.dtos.PatientDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentController {

    private static final Map<Integer, AppointmentDTO> appointmentMap = new HashMap<>();

    public static void addAppointments(){

        LocalDate localDate1 = LocalDate.of(2024, 3, 23);
        LocalDate localDate2 = LocalDate.of(2024, 1, 15);
        LocalDate localDate3 = LocalDate.of(2024, 1, 20);
        LocalDate localDate4 = LocalDate.of(2024, 2, 8);
        LocalDate localDate5 = LocalDate.of(2024, 4, 8);
        LocalTime localTime = LocalTime.of(15, 00);

        PatientDTO patientDTO1 = PatientController.getPatientFromMapWithId(1);
        PatientDTO patientDTO2 = PatientController.getPatientFromMapWithId(2);
        PatientDTO patientDTO3 = PatientController.getPatientFromMapWithId(3);
        PatientDTO patientDTO4 = PatientController.getPatientFromMapWithId(4);
        PatientDTO patientDTO5 = PatientController.getPatientFromMapWithId(5);

        AppointmentDTO appointmentDTO1 = new AppointmentDTO(localDate1,localTime, patientDTO1, 60, "Freud");
        AppointmentDTO appointmentDTO2 = new AppointmentDTO(localDate2,localTime, patientDTO2, 60, "Freud");
        AppointmentDTO appointmentDTO3 = new AppointmentDTO(localDate3,localTime, patientDTO3, 60, "Freud");
        AppointmentDTO appointmentDTO4 = new AppointmentDTO(localDate4,localTime, patientDTO4, 60, "Freud");
        AppointmentDTO appointmentDTO5 = new AppointmentDTO(localDate5,localTime, patientDTO5, 60, "Freud");

        appointmentMap.put(1, appointmentDTO1);
        appointmentMap.put(2, appointmentDTO2);
        appointmentMap.put(3, appointmentDTO3);
        appointmentMap.put(4, appointmentDTO4);
        appointmentMap.put(5, appointmentDTO5);

    }
    public Handler getUpcomingAppointments() {

        LocalDate dateOfToday = LocalDate.now();

        Map<Integer, AppointmentDTO> upcommingAppointments = appointmentMap.entrySet().stream()
                .filter(entrySet -> entrySet.getValue().getDateOfAppointment().isAfter(dateOfToday))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return ctx -> ctx.status(200).json(upcommingAppointments.values());
    }

    public Handler getById() {

        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            AppointmentDTO appointmentDTO = appointmentMap.get(id);
            if(appointmentDTO != null){
                ctx.json(appointmentDTO);
            }
            else{
                ctx.status(404);
                ctx.attribute("message", "No appointment with id: " + id);
            }
        };
    }
}
