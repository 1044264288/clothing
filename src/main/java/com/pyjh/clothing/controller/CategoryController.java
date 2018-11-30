package com.pyjh.clothing.controller;

import com.alibaba.fastjson.JSON;
import com.pyjh.clothing.entity.PageData;
import com.pyjh.clothing.service.CategoryService;
import com.pyjh.clothing.service.ProductService;
import com.pyjh.clothing.util.DateUtil;
import com.pyjh.clothing.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.pyjh.clothing.util.CodeMessage.*;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

@RestController
@RequestMapping(value = "category")
@Api(value = "category", description = "获取首页分类信息及商品")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @Resource
    ProductService productService;

    @ApiOperation("获取首页CategoryType，banner，商品列表")
    @RequestMapping(value = "/getHomeCategory", method = RequestMethod.POST)
    public String getHomeCategory(){
        //获取所有类别
        List<PageData> list = categoryService.getList();
        ArrayList<PageData> pageData1 = new ArrayList<>();
        if(!list.isEmpty()){
            for(int i = 0;i < list.size(); i++){
                PageData pageData = list.get(i);
                Integer cid = pageData.getInteger("id");
                //根据类别id查询product类中对应的recommendId的数据
                List<PageData> productList = productService.getListByRecommendId(cid);
                //把productList放入对应的category对象中
                pageData.put("productList",productList);
                //把category对象放入集合，转为JSON返回给前端；
                pageData1.add(pageData);
            }
            return Message.mesTrue(code_200, JSON.toJSONString(pageData1),message_200);
        }
        return Message.mesFalse(code_501, message_501);
    }


    @ApiOperation("获取所有CategoryType")
    @RequestMapping(value = "/getCategoryList", method = RequestMethod.POST)
    public String getCategoryList(){
        List<PageData> list = categoryService.getList();
        if(list.isEmpty()){
            return Message.mesFalse(code_501,message_501);
        }
        return Message.mesTrue(code_200, JSON.toJSONString(list),message_200);
    }

    @ApiOperation("添加CategoryType")
    @RequestMapping(value = "/addCategory",method = RequestMethod.POST)
    public String addCategory(@RequestParam("title")String title,
                               @RequestParam("banner_url")String banner_url){
        PageData pageData = new PageData();
        pageData.put("title",title);
        pageData.put("banner_url",banner_url);
        pageData.put("update_time", DateUtil.getTime());
        Integer integer = categoryService.addCategory(pageData);
        if(integer>0){
            return Message.mesTrue(code_200,JSON.toJSONString(integer),code_200);
        }
        return Message.mesFalse(code_401,message_401);
    }

    @ApiOperation("根据ID删除CategoryType")
    @RequestMapping(value = "deleteCategoryById",method = RequestMethod.POST)
    public String deleteCategoryById(@RequestParam("id") Integer id){
        Integer integer = categoryService.deleteCategoryById(id);
        if(integer>0){
            return Message.mesTrue(code_200,JSON.toJSONString(integer),message_200);
        }
        return Message.mesFalse(code_401,message_401);
    }

    @ApiOperation("根据ID修改CategoryType")
    @RequestMapping(value = "updateCategoryById",method = RequestMethod.POST)
    public String updateCategoryById(@RequestParam("id") Integer id,
                                     @RequestParam("title") String title,
                                     @RequestParam("banner_url") String banner_url){
        PageData pageData = new PageData();
        pageData.put("id",id);
        pageData.put("title",title);
        pageData.put("banner_url",banner_url);
        pageData.put("update_time",DateUtil.getTime());
        Integer integer = categoryService.updateCategoryById(pageData);
        if(integer>0){
            return Message.mesTrue(code_200, JSON.toJSONString(integer),message_200);
        }
        return Message.mesFalse(code_501,message_501);
    }

}
