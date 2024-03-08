package org.weeks.week5.part2_Veterinarian_exercise.controllers;

import io.javalin.http.Handler;
import org.weeks.week5.part2_Veterinarian_exercise.dtos.PatientDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientController implements IController {

    private static final Map<Integer, PatientDTO> patientMap = new HashMap<>();



/*
    @Override
    public Handler getAll() {
        boolean isExceptionTest = false; // To provoke a 500 error for demo purposes
        return ctx -> {
            if (isExceptionTest) {
                throw new ApiException(500, "Something went wrong in the getAll method in the PersonController");
            }
            ctx.json(persons);
        };
    }

 */

    @Override
    public Handler getAll() {
        return ctx -> {
            ctx.status(200).json(patientMap.values());
        };
    }

    @Override
    public Handler getById(){
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PatientDTO patientDTO = patientMap.get(id);
            if (patientDTO != null) {
                ctx.json(patientDTO);
            } else {
                ctx.status(404);
            ctx.attribute("message", "No patient with id: "+id);
                //
            }};
    }
    public static PatientDTO getPatientFromMapWithId(int id){

        return patientMap.get(id);
    }


    public static void addPatients() {

        List<String> deceases1 = new ArrayList<>();
        List<String> deceases2 = new ArrayList<>();
        List<String> deceases3 = new ArrayList<>();
        List<String> deceases4 = new ArrayList<>();
        List<String> deceases5 = new ArrayList<>();

        deceases1.add("infection");
        deceases2.add("virus");
        deceases3.add("broken body");
        deceases4.add("feather loss");
        deceases5.add("broken wing");
        deceases5.add("cough");

        PatientDTO patientDTO1 = new PatientDTO( "Tobias", 111, "dog", deceases1);
        PatientDTO patientDTO2 = new PatientDTO("Jonas", 222, "cat", deceases2);
        PatientDTO patientDTO3 = new PatientDTO( "Lykke", 333, "snake", deceases3);
        PatientDTO patientDTO4 = new PatientDTO( "Finn", 444, "parrot", deceases4);
        PatientDTO patientDTO5 = new PatientDTO( "Nana", 555, "bat", deceases5);

        patientMap.put(patientDTO1.getId(), patientDTO1);
        patientMap.put(patientDTO2.getId(), patientDTO2);
        patientMap.put(patientDTO3.getId(), patientDTO3);
        patientMap.put(patientDTO4.getId(), patientDTO4);
        patientMap.put(patientDTO5.getId(), patientDTO5);

        patientMap.forEach((key, value) -> System.out.println("Key: " + key + "\nValue: " + value));

    }
}