package org.weeks.week3.part5_Lombok;

public class Main {
    public static void main(String[] args) {

        Person person = new Person();
        Person person1 = new Person("Tobias", "Rossen", 34);
        System.out.println(person1.getFirstName());
        System.out.println(person1.getLastName());
        System.out.println(person1.getAge());
        System.out.println(person1);

    }
}
