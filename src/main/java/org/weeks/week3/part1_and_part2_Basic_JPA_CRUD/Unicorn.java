package org.weeks.week3.part1_and_part2_Basic_JPA_CRUD;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unicorn")
@NoArgsConstructor
public class Unicorn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;


    @Column(name = "power_strength")
    private int powerStrength;

    public Unicorn(String name, int age, int powerStrength) {
        this.name = name;
        this.age = age;
        this.powerStrength = powerStrength;
    }



    public Unicorn(int id, String name, int age, int powerStrength) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.powerStrength = powerStrength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPowerStrength() {
        return powerStrength;
    }

    public void setPowerStrength(int powerStrength) {
        this.powerStrength = powerStrength;
    }
}
