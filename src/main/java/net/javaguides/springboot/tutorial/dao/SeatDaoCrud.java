package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Seat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatDaoCrud extends CrudRepository<Seat, Long>{

    @Query("SELECT s.id FROM Seat s")
    List<Long> findAllIds();

//    @Query("SELECT s FROM Seat s ORDER BY s.timestamp DESC")
//    List<Seat> findAllOrderByTimestampDesc();

//    @Modifying
//    @Query(value = "alter table PRODUCTS add newonecolumn int not null default 0", nativeQuery = true)
//    void addColumn();
}
