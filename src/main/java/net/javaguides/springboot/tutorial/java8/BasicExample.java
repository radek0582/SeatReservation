package net.javaguides.springboot.tutorial.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//public class BasicExample {
//
//    private List<Book> books;
//
//    public BasicExample() {
//        init();
//        Optional<Book> book = books.stream().filter(element -> element.getName().equals("Książka Spring Boot 2")).findFirst();
//
//        book.ifPresent(System.out::println);
//    }
//
//    private void init() {
//        books = new ArrayList<>();
//        books.add(new Book("Książka Spring Boot 2", "9782123456803"));
//        books.add(new Book("Aplikcje internetowe", "9782123456803"));
//        books.add(new Book("Java dla zaawansowanych", "9782123456803"));
//    }
//
//    public static void main(String[] args) {
//        new BasicExample();
//    }
//}