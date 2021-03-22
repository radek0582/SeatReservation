package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.MoneyObj;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneyRepository extends CrudRepository<MoneyObj, Integer> {
    
    List<MoneyObj> findByAmount(int amount);

}
