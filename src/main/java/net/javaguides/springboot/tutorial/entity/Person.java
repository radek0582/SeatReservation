package net.javaguides.springboot.tutorial.entity;

import org.springframework.stereotype.Component;

//@Component (difference between bean and another spring bean (@component, @service, @repository, @controller))
public class Person {
    private final String name;
    private final int age;
    private final Gender gender;

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person (String name){
        this.age = 30;
        this.gender = Gender.FEMALE;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
