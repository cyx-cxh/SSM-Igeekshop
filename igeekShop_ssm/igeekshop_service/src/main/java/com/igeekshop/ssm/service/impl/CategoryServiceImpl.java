package com.igeekshop.ssm.service.impl;

import com.igeekshop.ssm.dao.CategoryMapper;
import com.igeekshop.ssm.domain.Category;
import com.igeekshop.ssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category cate) {
      categoryMapper.insert(cate);
    }

    @Override
    public List<Category> findAll() {

        return categoryMapper.selectByExample(null);
    }

    @Override
    public Category findByid(String cid) {
        return categoryMapper.selectByPrimaryKey(cid);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public void delete(String cid) {
        categoryMapper.deleteByPrimaryKey(cid);
    }




}
