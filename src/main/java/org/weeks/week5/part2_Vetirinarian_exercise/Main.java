package org.weeks.week5.part2_Vetirinarian_exercise;

import io.javalin.apibuilder.EndpointGroup;
import org.weeks.week5.part2_Vetirinarian_exercise.config.ApiConfig;
import org.weeks.week5.part2_Vetirinarian_exercise.controllers.AppointmentController;
import org.weeks.week5.part2_Vetirinarian_exercise.controllers.PatientController;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {

    public static void main(String[] args) {

        PatientController.addPatients();
        AppointmentController.addAppointments();

        ApiConfig
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
                path("/{id}", () -> {
                    get(patientController.getById());
                    delete(patientController.getById());
                    put(patientController.getById());
                });
            });
        };
    }

    private static EndpointGroup appointments() {
        AppointmentController appointmentController = new AppointmentController();
        return () -> {
            path("/appointments", () -> {
                get("/upcoming", appointmentController.getUpcomingAppointments());
                get("/{id}", appointmentController.getById());
            });
        };

    }

}

