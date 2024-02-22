package org.weeks.week3.part8_GLS_delivery;

import jakarta.persistence.EntityManagerFactory;

public class Main {

    static EntityManagerFactory emf;
    public static void main(String[] args) {

        emf = HibernateConfig.getEntityManagerFactoryConfig();

        Package package1 = new Package("X5Y8A3Z2", "Tobias",
                "Kirsten", DeliveryStatus.PENDING);

        PackageDAO packageDAO = new PackageDAO(emf);

        packageDAO.persistNewPackage(package1);
        Package packagePersisted = packageDAO.retreivePackage(1);
        System.out.println("Package persisted: " + packagePersisted);

        packageDAO.updateDeliveryStatus(1, DeliveryStatus.IN_TRANSIT);
        Package packageByTrackingNumber = packageDAO.retreivePackageByTrackingNumber("X5Y8A3Z2");
        System.out.println(packageByTrackingNumber);

        System.out.println(packageDAO.retreivePackage(1));
        packageDAO.deletePackage(1);

    }
}
