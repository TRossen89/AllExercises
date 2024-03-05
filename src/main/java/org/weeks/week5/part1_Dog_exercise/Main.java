package org.weeks.week5.part1_Dog_exercise;

import org.weeks.week5.part1_Dog_exercise.controllers.DogController;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

            Javalin app = Javalin.create(config -> config.routing.contextPath = "/api/dogs");

            DogController.addDogsToMap();

        app.routes(() -> {
                app.get("", DogController::getAllDogs);
                app.get("/{id}", DogController::getDogById);
                app.post("", DogController::createDog);
                app.put("/{id}", DogController::updateDog);
                app.delete("/{id}", DogController::deleteDog);
            });

/*
            app.get("api/dogs", DogController::getAllDogs);
            app.get("api/dogs/{id}", DogController::getDogById);

            app.post("api/dogs/{id}", DogController::createDog);
            app.put("api/dogs/{id}", DogController::updateDog);
            app.delete("api/dogs/{id}", DogController::deleteDog);

 */

            app.start(7007);
        }
    }

