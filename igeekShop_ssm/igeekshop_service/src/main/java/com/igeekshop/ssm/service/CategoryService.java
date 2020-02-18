package com.igeekshop.ssm.service;

import com.igeekshop.ssm.domain.Category;


import java.util.List;

public interface CategoryService {
    void add(Category cate);

    List<Category> findAll();

    Category findByid(String cid);

    void update(Category category);

    void delete(String cid);


}
