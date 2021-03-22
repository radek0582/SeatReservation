package net.javaguides.springboot.tutorial.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams {
    public static void main (String [] args) throws IOException{

//        String [] names = {"Al", "Hans", "Shiva", "Sara", "Tom", "sean", "Jane", "jane", "Shade"};
//
//        Arrays.stream(names)
//                .filter(x->x.startsWith("S"))
//                .sorted()
//                .forEach(System.out::println);

//        List<String> people = Arrays.asList("Al", "Tom", "Michael", "Joe", "Damien", "jamie", "tip");
//
//        people
//                .stream()
//                .map(String::toLowerCase)
//                .filter(x->x.startsWith("j"))
//                .forEach(System.out::println);
        System.out.println(System.getProperty("user.dir"));
//        Stream<String> bands = Files.lines(Paths.get("bands.txt"));
//
//
//        bands
//                .sorted()
//                .filter(x->x.length()>13)
//                .forEach(System.out::println);
//
//        bands.close();
        Stream <String> rows1 = Files.lines(Paths.get("data.txt"));



        int rowCount = (int)rows1
                .map(row -> row.split(","))
                .filter(row -> row.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();
    }
}
