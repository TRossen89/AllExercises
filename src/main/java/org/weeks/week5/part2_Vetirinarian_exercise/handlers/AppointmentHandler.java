package org.weeks.week5.part2_Vetirinarian_exercise.handlers;


import org.weeks.week5.part1_Dog_exercise.dtos.DogDTO;
import org.weeks.week5.part2_Vetirinarian_exercise.dtos.AppointmentDTO;
import io.javalin.http.Context;
import org.weeks.week5.part2_Vetirinarian_exercise.model.Appointment;
import org.weeks.week5.part2_Vetirinarian_exercise.model.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentHandler {

    private static final Map<Integer, Appointment> appointmentMap = new HashMap<>();


    public static void getUpcomingAppointments(Context ctx) {
        // Implement logic to retrieve a list of upcoming appointments
    }

    public static void getAppointmentById(Context ctx) {
        // Implement logic to retrieve details of a specific appointment by appointment ID
    }

    public static void addAppointments() {
    }
}
