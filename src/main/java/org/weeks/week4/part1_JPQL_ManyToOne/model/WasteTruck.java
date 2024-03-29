package org.weeks.week4.part1_JPQL_ManyToOne.model;

import lombok.ToString;
import org.weeks.week4.part1_JPQL_ManyToOne.config.HibernateConfig;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "waste_truck")
//@NamedQuery(name="WasteTruck.deleteAll", query = "DELETE FROM WasteTruck")
public class WasteTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    String brand;
    Integer capacity;

    Boolean is_available;

    @Column(unique = true)
    String registrationNumber;

    @ToString.Exclude
    @OneToMany(mappedBy = "waste_truck")
    Set<Driver> drivers = new HashSet<>();

    public WasteTruck(String brand, Integer capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }

    public void addDriver(Driver driver){

        this.drivers.add(driver);
        if(driver!=null){
            driver.setWaste_truck(this);
        }
    }

    public void removeDriver(Driver driver){

        this.drivers.remove(driver);
        if(driver!=null){
            driver.setWaste_truck(this);
        }
    }


}

