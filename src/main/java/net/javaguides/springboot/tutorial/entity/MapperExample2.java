package net.javaguides.springboot.tutorial.entity;

import net.javaguides.springboot.tutorial.dao.CommentDao;
import net.javaguides.springboot.tutorial.dao.ProductDao;
import net.javaguides.springboot.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapperExample2 {

    public static void main(String[] args){
//        List<String> names = Arrays.asList("Tom", "George", "Michael");



        List <Product> products = new ArrayList<>();
        List <Product> productsNotFarel = new ArrayList<>();
        List <Product> productsNotFarel2 = new ArrayList<>();
        List <Comment> comments1 = new ArrayList<>();

        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();
        Product p4 = new Product();
        Product p5 = new Product();


        Comment c1 = new Comment();

        comments1.add(c1);




        p1.setDescription("kosiarka elektryczna");
        p1.setName("Kosiarka");
        p1.setVisitCount(1);
        p1.setComments(comments1);
        p1.setImagePath("kos.jpg");

        p2.setDescription("kosiarka spalinowa");
        p2.setName("Kosiarka Stihl");
        p2.setVisitCount(1);
        p2.setComments(comments1);
        p2.setImagePath("kos.jpg");

        p3.setDescription("kosiarka manualna");
        p3.setName("Kosiarka Farel");
        p3.setVisitCount(1);
        p3.setComments(comments1);
        p3.setImagePath("kos.jpg");

        p4.setDescription("kosiarka manualna");
        p4.setName("Kosiarka XXXXX");
        p4.setVisitCount(1);
        p4.setComments(comments1);
        p4.setImagePath("kos.jpg");

        p5.setDescription("kosiarka manualna");
        p5.setName("Kosiarka");
        p5.setVisitCount(1);
        p5.setComments(comments1);
        p5.setImagePath("kos.jpg");

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);



        System.out.println("Imperative style: ");

//        for (String name : names){
//            if (!name.equals("George")){
//                Person person = new Person(name, 10, Gender.MALE);
//            }
//        }

        System.out.println("Functional style: ");

        products.stream()
                .filter(product -> isNotFarel(product))
                .map(productNew -> productsNotFarel.add(productNew))
                .forEach(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean x) {
                        int i = productsNotFarel.size()-1;
                        System.out.println(productsNotFarel.get(i).getName());
                    }
                });


        ;


//        List<String> mNames = names.stream()
//                .filter(s -> s.contains("M"))
//                .collect(Collectors.toList());
//
//        names
//                .forEach(s -> System.out.println(s));
//
//        long count = names.stream()
//                .count();
//
//        System.out.println(count);
//
//        MoonWithLombok moonWithLombok = new MoonWithLombok();
//
//        moonWithLombok.getName();
    }

    private static boolean isNotFarel(Product product){
        return !product.getName().equals("Kosiarka Farel");
    }
}
