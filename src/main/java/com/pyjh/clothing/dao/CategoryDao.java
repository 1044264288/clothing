package com.pyjh.clothing.dao;

import com.pyjh.clothing.entity.PageData;
import org.apache.ibatis.annotations.Mapper;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by Administrator on 2018/11/29 0029.
 */
@Mapper
public interface CategoryDao {
    List<PageData> getList();

    Integer addCategory(PageData pageData);

    Integer deleteCategoryById(Integer id);

    Integer updateCategoryById(PageData pageData);
}
