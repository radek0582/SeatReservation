package net.javaguides.springboot.tutorial.entity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapperExample {
    public static void main (String [] args){
        List<String> names = Arrays.asList("Tom", "George", "Michael");

        System.out.println("Imperative style: ");

        for (String name : names){
            if (!name.equals("George")){
                Person person = new Person(name, 10, Gender.MALE);
            }
        }

        System.out.println("Functional style: ");

        names.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String name1) {
                        return isNotSam(name1);
                    }
                })
                .map(name -> new Person(name))
                .forEach(x -> System.out.println(x));

        List<String> mNames = names.stream()
                .filter(s -> s.contains("M"))
                .collect(Collectors.toList());

        names
                .forEach(s -> System.out.println(s));

        long count = names.stream()
                .count();

        System.out.println(count);

        MoonWithLombok moonWithLombok = new MoonWithLombok();

        moonWithLombok.getName();
    }

    private static boolean isNotSam(String name){
        return !name.equals("Sam");
    }
}
