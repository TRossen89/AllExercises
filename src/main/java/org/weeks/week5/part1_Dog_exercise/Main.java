package org.weeks.week5.part1_Dog_exercise;

import org.weeks.week5.part1_Dog_exercise.controllers.DogController;
import io.javalin.Javalin;
import org.weeks.week5.part1_Dog_exercise.dtos.DogDTO;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<Integer, DogDTO> dogMap = new HashMap<>();

        DogDTO dogDTO1 = new DogDTO(1, "Labrador", "Something", "female", 8);
        DogDTO dogDTO2 = new DogDTO(2, "Collie", "Something", "male", 6);
        dogMap.put(1, dogDTO1);
        dogMap.put(2, dogDTO2);

        Javalin app = Javalin.create(config ->
        {
            config.http.defaultContentType = "application/json";
            config.routing.contextPath = "/api/dogs";
        });

        //DogController.addDogsToMap();

        DogController dogController = new DogController();
        app.routes(() -> {
            app.get("", ctx -> dogController.getAllDogs(ctx, dogMap));
            app.get("/{id}", ctx -> dogController.getDogById(ctx, dogMap));
            app.post("/create", ctx -> dogController.createDog(ctx, dogMap));

        });

        app.start(7007);
    }
}

