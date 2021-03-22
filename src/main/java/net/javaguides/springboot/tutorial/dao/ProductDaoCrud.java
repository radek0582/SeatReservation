package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDaoCrud extends CrudRepository<Product, Long>{

    @Query("SELECT p.id FROM Product p")
    List<Long> findAllIds();

    @Query("SELECT p FROM Product p ORDER BY p.timestamp DESC")
    List<Product> findAllOrderByTimestampDesc();

    @Query("SELECT p FROM Product p ORDER BY p.name")
    List<Product> findAllByNameAsc();


//    @Modifying
//    @Query(value = "alter table PRODUCTS add newonecolumn int not null default 0", nativeQuery = true)
//    void addColumn();
}
