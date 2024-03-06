package org.weeks.week5.part2_Vetirinarian_exercise.controllers;


import io.javalin.http.Context;
import org.weeks.week5.part2_Vetirinarian_exercise.model.Appointment;

import java.util.HashMap;
import java.util.Map;

public class AppointmentController {

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
