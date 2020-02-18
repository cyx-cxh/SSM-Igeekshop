package com.igeekshop.ssm.service.impl;

import com.igeekshop.ssm.dao.ProductMapper;
import com.igeekshop.ssm.domain.Product;
import com.igeekshop.ssm.domain.ProductExample;
import com.igeekshop.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
@Autowired
private ProductMapper productMapper;
    @Override
    public List<Product> findAll() {
        return productMapper.selectByExample(null);
    }

    @Override
    public Product findById(String pid) {



        return productMapper.selectByPrimaryKey(pid);
    }

    @Override
    public void update(Product p) {

        productMapper.updateByPrimaryKey(p);
    }

    @Override
    public void delete(String pid) {
productMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public void addProduct(Product product) {
productMapper.insert(product);
    }

    @Override
    public List<Product> findHotProducts() {
       return productMapper.findHotProducts();
    }

    @Override
    public List<Product> findNewProducts() {
        return productMapper.findNewProducts();
    }

    @Override
    public List<Product> findByNameAndCid(String pname, String cid) {
        return productMapper.findByNameAndCid(pname,cid);
    }

}
