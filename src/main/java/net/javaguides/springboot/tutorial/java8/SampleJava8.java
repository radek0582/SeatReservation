package net.javaguides.springboot.tutorial.java8;


import java.util.Optional;
import java.util.function.Supplier;

//public class SampleJava8 {
//    public static void main (String [] args){
//        Optional<String> name = Optional.empty();
//
//        name.ifPresent(name1-> System.out.println(name1));
//
////        name = Optional.of("John");
////
////        name.ifPresent(name1-> System.out.println(name1));
//
//        name.map(name2 -> name2.toUpperCase()).ifPresent(name3 -> System.out.println(name3));
//
////        String name4 = "Jakub";
//
//        String name4 = null;
//
////        String notLogged = Optional.ofNullable(name4).orElse("notLogged");
//        String notLogged = Optional.ofNullable(name4).orElseGet(() -> {
//            System.out.println("Probuje pobrac maila");
//
//
//
//            return "Tomasz";
//        });
//
//        System.out.println(notLogged);
//
//    }
//}
