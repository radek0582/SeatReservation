package net.javaguides.springboot.tutorial.repository;


import net.javaguides.springboot.tutorial.entity.State;
import net.javaguides.springboot.tutorial.entity.manytomany.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findByName(String name);
}
