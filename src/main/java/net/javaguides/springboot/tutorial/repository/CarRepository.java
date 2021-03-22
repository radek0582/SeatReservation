package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.manytomany.Car;
import net.javaguides.springboot.tutorial.entity.manytomany.Magazine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByName(String name);


}
