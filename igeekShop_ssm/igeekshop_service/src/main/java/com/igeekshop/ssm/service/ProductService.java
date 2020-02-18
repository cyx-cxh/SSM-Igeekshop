package com.igeekshop.ssm.service;

import com.igeekshop.ssm.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(String pid);

    void update(Product p);
    void delete(String pid);

    void addProduct(Product product);

    List<Product> findHotProducts();

    List<Product> findNewProducts();
    List<Product> findByNameAndCid( String pname,  String cid);


}
