package org.weeks.week5.part2_Vetirinarian_exercise;

import io.javalin.Javalin;

import io.javalin.apibuilder.EndpointGroup;
import org.weeks.week5.part2_Vetirinarian_exercise.config.ApplicationConfig;
import org.weeks.week5.part2_Vetirinarian_exercise.controllers.AppointmentController;
import org.weeks.week5.part2_Vetirinarian_exercise.controllers.PatientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {

    public static void main(String[] args) {

        PatientController.addPatients();
        AppointmentController.addAppointments();

        ApplicationConfig
                .getInstance()
                .initiateServer()
                .errorHandling()
                .startServer(7007)
                .setRoutes(patients())
                .setRoutes(appointments());
    }

    private static EndpointGroup patients() {
        PatientController patientController = new PatientController();
        return () -> {
            path("/patients", () -> {
                get("/", patientController.getAll());
                get("/{id}", patientController.getById());
            });
        };
    }

    private static EndpointGroup appointments(){
        AppointmentController appointmentController = new AppointmentController();
        return () -> {
            path("/appointments", () -> {
                get("/upcoming", appointmentController.getUpcomingAppointments());
                get("/{id}", appointmentController.getById());});
        };

    }

}

