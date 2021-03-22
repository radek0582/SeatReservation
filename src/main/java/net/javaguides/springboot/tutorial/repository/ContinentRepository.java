package net.javaguides.springboot.tutorial.repository;


import net.javaguides.springboot.tutorial.entity.manytomany.Company;
import net.javaguides.springboot.tutorial.entity.manytomany.Continent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContinentRepository extends CrudRepository<Continent, Long> {
    List<Continent> findByName(String name);
}
