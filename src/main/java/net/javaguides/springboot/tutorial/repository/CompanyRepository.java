package net.javaguides.springboot.tutorial.repository;


import net.javaguides.springboot.tutorial.entity.manytomany.Client;
import net.javaguides.springboot.tutorial.entity.manytomany.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findByName(String name);
}
