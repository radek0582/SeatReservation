package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.dao.ProductDao;
import net.javaguides.springboot.tutorial.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDaoImpl;

    @Override
    public List<Product> getList (){
        return productDaoImpl.findAll();
    }

    public Product findById (Long id) throws ProductNotFoundException{
        Product product = productDaoImpl.findById(id);

//        if (product == null){
//            throw new ProductNotFoundException();
//        }else{
//            product.increaseVisitCount();
//            productDaoImpl.update(product);
//        }

        return getMemAndUpdate(product);
    }

    @Override
    public Product findRandom () throws ProductNotFoundException{
        Product product = productDaoImpl.findRandom();
        return getMemAndUpdate(product);
    }

    @Override
    public void save (Product product){
        productDaoImpl.save(product);
    }

    private Product getMemAndUpdate(Product product) throws ProductNotFoundException{
        if (product == null) {

            throw new ProductNotFoundException();
        } else {
            product.increaseVisitCount();
            productDaoImpl.update(product);
        }
        return product;
    }

    @Override
    public void delete(Product product){
        productDaoImpl.delete(product);
    }
}
