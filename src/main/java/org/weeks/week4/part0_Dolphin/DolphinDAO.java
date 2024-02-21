package org.weeks.week4.part0_Dolphin;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NamedQuery;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.sql.ast.Clause.FROM;

public class DolphinDAO {

    EntityManagerFactory entityManagerFactory;


    public DolphinDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void createPerson(Person person){

        try (var em = this.entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            try{
                // Managed state
                em.persist(person);
            }catch (RuntimeException e){
                System.out.println(e);
            }
            // Detached state
            em.getTransaction().commit();
        }
    }


    public Person readPerson(int id){
        try (var em = entityManagerFactory.createEntityManager()) {

            // DB, managed, detached?
            return em.find(Person.class, id);
        }
    }

    public List<Note> getPersonNotes(Integer id){
        try (var em = entityManagerFactory.createEntityManager()) {

            var query = em.createQuery("SELECT p.notes FROM Person p JOIN p.notes n WHERE p.id = :id", Note.class)
                    .setParameter("id", id.toString());

            // Managed and detached
            return query.getResultList();// DB, managed, detached?

        }
    }

    public int getPersonTotalAmountPaid(int id){
        try (var em = entityManagerFactory.createEntityManager()) {

            var query = em.createQuery("SELECT p.fees FROM Person p JOIN p.fees n", Fee.class);

            List<Fee> allFees = query.getResultList();

            int totalAmount = allFees.stream().collect(Collectors.summingInt(Fee::getAmount));

            return totalAmount;

        }
    }


    public void updatePerson(int id, Person person){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            // DB, managed and then transient?
            var personInDB = em.find(Person.class, id);


            //Transient?
            personInDB.setName(person.getName());
            personInDB.setPersonDetail(person.getPersonDetail());

            // Managed, DB
            em.merge(personInDB);

            // Detached
            em.getTransaction().commit();
        }
    }


    public void deletePerson(int id){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            // DB and then managed
            var person = em.find(Person.class, id);

            // Managed
            em.remove(person);

            // Removed
            em.getTransaction().commit();
        }
    }

    public List<NoteDTO> readAllNotesWithNameAndAgeOfPersonDTO(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT new org.weeks.week4.part0_Dolphin.NoteDTO(p.name, d.age, n.note)" +
                    "FROM Person p JOIN p.personDetail d JOIN p.notes n", NoteDTO.class);

            // Managed and detached
            return query.getResultList();
        }
    }

    public List<PersonDTO> readAllPersonsDTO(){

        try (var em = entityManagerFactory.createEntityManager()) {
            // DB, managed
            var query = em.createQuery("SELECT new org.weeks.week4.part0_Dolphin.PersonDTO(p.id, p.name, f.amount, f.payDate)" +
                    "FROM Person p JOIN p.fees f", PersonDTO.class);

            // Managed and detached
            return query.getResultList();
        }
    }

}
