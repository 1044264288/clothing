package com.pyjh.clothing.service;

import com.pyjh.clothing.entity.PageData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/11/29 0029.
 */
@Service
public interface CategoryService {

    public List<PageData> getList();

    public Integer addCategory(PageData pageData);

    public Integer deleteCategoryById(Integer id);

    public Integer updateCategoryById(PageData pageData);
}
