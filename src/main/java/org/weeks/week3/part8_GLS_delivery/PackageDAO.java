package org.weeks.week3.part8_GLS_delivery;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class PackageDAO {
    EntityManagerFactory entityManagerFactory;

    public PackageDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void persistNewPackage(Package package1){

        try (var em = this.entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            try{
                // Managed state
                em.persist(package1);
            }catch (RuntimeException e){
                System.out.println(e);
            }
            // Detached state
            em.getTransaction().commit();
        }
    }


    public Package retreivePackage(int id){
        try (var em = entityManagerFactory.createEntityManager()) {

            // DB, managed, detached?
            return em.find(Package.class, id);
        }
    }

    public Package retreivePackageByTrackingNumber(String trackingNumber){

        try (var em = entityManagerFactory.createEntityManager()) {
            TypedQuery<Package> package1 =
                    em.createQuery("SELECT a FROM Package a WHERE a.trackingNumber = :trackNum", Package.class)
                    .setParameter("trackNum", trackingNumber);

            return package1.getResultList().get(0);
        }
    }


    public void updateDeliveryStatus(int packageId, DeliveryStatus status){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            // DB, managed and then transient?
            var packageInDB = em.find(Package.class, packageId);

            //Transient?
            packageInDB.setDeliveryStatus(status);
            // Managed, DB
            em.merge(packageInDB);

            // Detached
            em.getTransaction().commit();
        }
    }

    public void deletePackage(int id){

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            // DB and then managed
            var package1 = em.find(Package.class, id);

            // Managed
            em.remove(package1);

            // Removed
            em.getTransaction().commit();
        }
    }
}
