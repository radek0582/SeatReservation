package net.javaguides.springboot.tutorial.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SampleJava8Test {
    @Test
    public void testFilter() throws Exception {
        List<String> fruits= new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("orange");

        long count = fruits.stream().filter(element -> element.contains("n")).count();
        assertEquals(2,count);
    }

    @Test
    public void testFilter2() throws Exception {
        List<String> fruits= new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("banana");
        fruits.add("orange");

        Set<String> count = fruits.stream().filter(element -> element.contains("z")).collect(Collectors.toSet());
        long number = fruits.stream().filter(unit -> unit.contains("pp")).count();
        System.out.println(count);
        assertEquals(1, number);
    }
}