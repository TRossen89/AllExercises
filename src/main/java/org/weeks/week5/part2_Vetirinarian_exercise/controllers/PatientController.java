package org.weeks.week5.part2_Vetirinarian_exercise.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.weeks.week5.part2_Vetirinarian_exercise.model.Patient;

import java.util.HashMap;
import java.util.Map;

public class PatientController implements IController {

    private static final Map<Integer, Patient> patientMap = new HashMap<>();
    //private static final Gson gson = new Gson();


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
            ctx.status(HttpStatus.OK).json(patientMap.values());
        };
    }

    @Override
    public Handler getById(){
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Patient patient = patientMap.get(id);
            if (patient != null) {
                ctx.json(patient);
            } else {
                ctx.status(404);
            ctx.attribute("message", "No patient with id: "+id);
                //
            }};
    }
    public static void getPatientDetailsById(Context ctx) {



    }

    public static void getAllPatients(Context ctx) {
        ctx.json(patientMap.values());
    }

    public static void addPatients() {

        Patient patient1 = new Patient( "Tobias", 111);
        Patient patient2 = new Patient("Jonas", 222);
        Patient patient3 = new Patient( "Lykke", 333);
        Patient patient4 = new Patient( "Finn", 444);
        Patient patient5 = new Patient( "Nana", 555);

        patientMap.put(patient1.getId(), patient1);
        patientMap.put(patient2.getId(), patient2);
        patientMap.put(patient3.getId(), patient3);
        patientMap.put(patient4.getId(), patient4);
        patientMap.put(patient5.getId(), patient5);

        patientMap.forEach((key, value) -> System.out.println("Key: " + key + "\nValue: " + value));

    }
}