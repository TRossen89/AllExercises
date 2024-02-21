package org.weeks.week4.part0_Dolphin;

import jakarta.persistence.EntityManagerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("week4", false);

        /*try(EntityManager em = emf.createEntityManager())
        {
            Person p1 = new Person("Hanzi");
            PersonDetail pd1 = new PersonDetail("Algade 3", 4300, "Holbæk", 45);
            p1.addPersonDetail(pd1);
            Fee f1 = new Fee(125, LocalDate.of(2023, 8, 25));
            Fee f2 = new Fee(150, LocalDate.of(2023, 7, 19));
            p1.addFee(f1);
            p1.addFee(f2);

            em.getTransaction().begin();
                em.persist(p1);
            em.getTransaction().commit();
        }

         */


        Person p1 = new Person("Hanzi");
        Person p2 = new Person("Orla");
        PersonDetail pd1 = new PersonDetail("Algade 3", 4300, "Holbæk", 45);
        PersonDetail pd2 = new PersonDetail("Algade 18", 4300, "Holbæk", 60);
        p1.addPersonDetail(pd1);
        p2.addPersonDetail(pd2);
        Fee f1 = new Fee(125, LocalDate.of(2023, 8, 25));
        Fee f2 = new Fee(150, LocalDate.of(2023, 7, 19));
        Fee f3 = new Fee(300, LocalDate.of(2021, 5, 11));

        p1.addFee(f1);
        p1.addFee(f2);

        p2.addFee(f3);


        Person admin1 = new Person("Karoline");
        PersonDetail adminDetail1 = new PersonDetail("Samsøvej 31", 4300, "Holbæk", 28);
        admin1.addPersonDetail(adminDetail1);

        // Adding not to person
        Note note1 = new Note("This person is great", admin1 );
        Note note2 = new Note("This person is the first person", admin1);

        Note note3 = new Note("This person is more than great", admin1);

        p1.addNote(note1);
        p1.addNote(note2);

        p2.addNote(note3);

        // Persisting person with note and admin who created note
        DolphinDAO dolphinDAO = new DolphinDAO(emf);
        dolphinDAO.createPerson(admin1);
        dolphinDAO.createPerson(p1);
        dolphinDAO.createPerson(p2);


        // Retrieving and printing out person
        Person personPersisted = dolphinDAO.readPerson(2);
        System.out.println(personPersisted.getName());

        // Retrieving notes of person with id 2
        List<Note> notesOfPerson1 = dolphinDAO.getPersonNotes(2);
        System.out.println(notesOfPerson1);

        // Getting total amount paid of person 2
        System.out.println(dolphinDAO.getPersonTotalAmountPaid(2));

        // Getting all notes with name and age of person who wrote note
        List<NoteDTO> allNotesOfPerson1 = dolphinDAO.readAllNotesWithNameAndAgeOfPersonDTO();
        allNotesOfPerson1.forEach(System.out::println);



        /*
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");


        String dateOfEmployment = formatter.format(date).replaceAll("-", "");

        System.out.println(dateOfEmployment);

        /*
        List<PersonDTO> allPersons = dolphinDAO.readAllPersonsDTO();

        System.out.println(allPersons);

         */

    }
}