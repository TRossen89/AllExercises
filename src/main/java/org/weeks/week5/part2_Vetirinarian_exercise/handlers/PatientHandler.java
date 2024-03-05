package org.weeks.week5.part2_Vetirinarian_exercise.handlers;

import com.google.gson.Gson;
import org.weeks.week5.part1_Dog_exercise.dtos.DogDTO;
import org.weeks.week5.part2_Vetirinarian_exercise.dtos.PatientDTO;
import io.javalin.http.Context;
import org.weeks.week5.part2_Vetirinarian_exercise.model.Appointment;
import org.weeks.week5.part2_Vetirinarian_exercise.model.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientHandler {

    private static final Map<Integer, Patient> patientMap = new HashMap<>();
    private static final Gson gson = new Gson();


    public static void getPatientDetailsById(Context ctx) {

        int id = Integer.parseInt(ctx.pathParam("id"));
        Patient patient = patientMap.get(id);
        if (patient != null) {
            ctx.json(patient);
        } else {
            ctx.status(404).json("Patient not found");
        }
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