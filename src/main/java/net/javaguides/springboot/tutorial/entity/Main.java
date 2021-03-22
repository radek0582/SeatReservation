package net.javaguides.springboot.tutorial.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args){
        List<Person> people = getPeople();

//        List<Person> females = new ArrayList<>();
//
//        for (Person person : people){
//            if (person.getGender().equals(Gender.FEMALE))
//                females.add(person);
//        }
//
//        females.forEach(System.out::println);

        List<Person> females = people
                .stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

//        females.forEach(System.out::println);
        List<Person> sorted = people
                .stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());

        sorted.forEach(System.out::println);
    }

    private static List <Person> getPeople(){
        List<Person> people = new ArrayList<Person>();

        people.add(new Person("James Bond", 20, Gender.MALE));
        people.add(new Person("Alina Smith", 33, Gender.FEMALE));
        people.add(new Person("Helen White", 57, Gender.FEMALE));
        people.add(new Person("Alex Boz", 14, Gender.MALE));

        return people;
    }
}
