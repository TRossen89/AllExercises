package org.weeks.week5.part2_Vetirinarian_exercise;

import io.javalin.Javalin;

import org.weeks.week5.part2_Vetirinarian_exercise.handlers.AppointmentHandler;
import org.weeks.week5.part2_Vetirinarian_exercise.handlers.PatientHandler;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> config.routing.contextPath = "/api/vet/patients");

        //AppointmentHandler.addAppointments();
        PatientHandler.addPatients();

        app.routes(() -> {
            app.get("", PatientHandler::getAllPatients);
            app.get("/{id}", ctx -> PatientHandler.getPatientDetailsById(ctx));

        });

            /*
            app.post("", DogController::createDog);
            app.put("/{id}", DogController::updateDog);
            app.delete("/{id}", DogController::deleteDog);

*/



/*
            app.get("api/dogs", DogController::getAllDogs);
            app.get("api/dogs/{id}", DogController::getDogById);

            app.post("api/dogs/{id}", DogController::createDog);
            app.put("api/dogs/{id}", DogController::updateDog);
            app.delete("api/dogs/{id}", DogController::deleteDog);

 */

        app.start(7071);
    }
}

