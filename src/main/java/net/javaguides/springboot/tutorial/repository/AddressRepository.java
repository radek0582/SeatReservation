package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.onetoone.Address;
import net.javaguides.springboot.tutorial.entity.onetoone.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByName(String name);
}
