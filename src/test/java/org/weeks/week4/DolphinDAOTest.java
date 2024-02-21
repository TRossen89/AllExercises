package org.weeks.week4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import org.weeks.week4.part0_Dolphin.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DolphinDAOTest {
    EntityManagerFactory emfTest;
    DolphinDAO dolphinDAO;



    @BeforeAll
    void beforeAll() {
        emfTest = HibernateConfig.getEntityManagerFactoryConfig("week4", true);

    }

    @BeforeEach
    void setÙp() {

        dolphinDAO = new DolphinDAO(emfTest);

        Person p1 = new Person("Tobias");
        Person p2 = new Person("Jonas");
        Person p3 = new Person("Lykke");
        Person admin = new Person("Finn");

        Fee f1 = new Fee(200, LocalDate.of(2001, 8, 14));

        Note n1 = new Note("Test note 1", admin);
        Note n2 = new Note("Test note 2", admin);
        Note n3 = new Note("Test note 3", admin);

        p1.addNote(n1);
        p1.addNote(n2);

        p1.addFee(f1);

        p2.addFee(f1);

        p3.addNote(n3);


        try (EntityManager em = emfTest.createEntityManager()) {

            em.getTransaction().begin();
            em.createQuery("DELETE FROM Person").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE person_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE fee_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE note_id_seq RESTART WITH 1").executeUpdate();


            em.persist(admin);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        }
    }


    @Test
    void getPersonNotes(){

        // Arrange
        Person admin = new Person("Finn");
        Note note1 = new Note("Test note 1", admin);
        Note note2 = new Note("Test note 2", admin);

        // Act
        List<Note> actual = dolphinDAO.getPersonNotes(2);

        // Assert
        assertEquals(note1.getNote(), actual.get(0).getNote());
        assertEquals(note2.getNote(), actual.get(1).getNote());

    }
}
