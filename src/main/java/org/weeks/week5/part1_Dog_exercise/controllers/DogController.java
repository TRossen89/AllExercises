package org.weeks.week5.part1_Dog_exercise.controllers;

import io.javalin.http.Handler;
import org.weeks.week5.part1_Dog_exercise.dtos.DogDTO;
import com.google.gson.Gson;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class DogController {

    //private static Map<Integer, DogDTO> dogMap = new HashMap<>();


    public static void addDogsToMap(Map<Integer, DogDTO> dogMap){
        DogDTO dogDTO1 = new DogDTO(1, "Labrador", "Something", "female", 8);
        DogDTO dogDTO2 = new DogDTO(2, "Collie", "Something", "male", 6);
        dogMap.put(1, dogDTO1);
        dogMap.put(2, dogDTO2);
    }

    public void getAllDogs(Context ctx, Map<Integer, DogDTO> dogMap) {
        ctx.json(dogMap.values());
    }

    public void getDogById(Context ctx, Map<Integer, DogDTO> dogMap) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogDTO dog = dogMap.get(id);
        if (dog != null) {
            ctx.json(dog);
        } else {
            ctx.status(404).json("Dog not found");
        }
    }

    public void createDog(Context ctx, Map<Integer, DogDTO> dogMap) {
            DogDTO newDog = ctx.bodyAsClass(DogDTO.class);
            dogMap.put(newDog.getId(), newDog);
            ctx.status(201).json("Dog created");
        }


    public void updateDog(Context ctx, Map<Integer, DogDTO> dogMap) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogDTO updatedDog = ctx.bodyAsClass(DogDTO.class);
        if (dogMap.containsKey(id)) {
            updatedDog.setId(id);
            dogMap.put(id, updatedDog);
            ctx.json(updatedDog);
        } else {
            ctx.status(404).json("Dog not found");
        }
    }

    public void deleteDog(Context ctx, Map<Integer, DogDTO> dogMap) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (dogMap.containsKey(id)) {
            DogDTO deletedDog = dogMap.remove(id);
            ctx.json(deletedDog);
        } else {
            ctx.status(404).json("Dog not found");
        }
    }

}
