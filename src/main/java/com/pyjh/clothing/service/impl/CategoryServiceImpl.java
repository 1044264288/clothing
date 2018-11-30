package com.pyjh.clothing.service.impl;

import com.pyjh.clothing.dao.CategoryDao;
import com.pyjh.clothing.entity.PageData;
import com.pyjh.clothing.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by Administrator on 2018/11/29 0029.
 */
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryDao categoryDao;

    @Override
    public List<PageData> getList() {
        List<PageData> list = categoryDao.getList();
        return list;
    }

    public Integer addCategory(PageData pageData) {
        return categoryDao.addCategory(pageData);
    }

    @Override
    public Integer deleteCategoryById(Integer id) {
        return categoryDao.deleteCategoryById(id);
    }

    @Override
    public Integer updateCategoryById(PageData pageData) {
        return categoryDao.updateCategoryById(pageData);
    }
}
