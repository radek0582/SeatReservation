package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductDaoCrud productDaoCrud;

    @Override
    public List<Product> findAll() {
        Iterable<Product> coll = productDaoCrud.findAllOrderByTimestampDesc();
        List<Product> products = new ArrayList<>();

//        List<Product> sortedList = products.stream()
//                .sorted(Comparator.comparingInt(Product::getTimestamp))
//                .collect(Collectors.toList());

        coll.forEach(products::add);    // java 8
        return products;
    }

    private Sort sortById (){
        return new Sort(Sort.Direction.ASC, "timestamp");
    }

    @Override
    public Product findById(Long id) {
        return productDaoCrud.findById(id).get();
    }

    @Override
    public void update(Product product) {
        this.save(product);
    }

    @Override
    public Product findRandom(){
        List<Long> allIds = productDaoCrud.findAllIds();
        int listSize = allIds.size();
        Random random = new Random();
        int randomIndex = random.nextInt(listSize);

        if (randomIndex < 0) {
            return null;
        } else {
            long randomID = allIds.get(randomIndex);
            return findById(randomID);
        }
    }

    @Override
    public void save(Product product) {
        productDaoCrud.save(product);
    }

    @Override
    public void delete(Product product){
        productDaoCrud.delete(product);
    }
}
