package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {
    //查询所有product,带条件,分页
    List<Product> findAll(Integer pageNum, Integer pageSize,String productName) throws Exception;

    //添加product
    void addProduct(Product product) throws Exception;

    //批量删除product
    void delProduct(String[] ids) throws Exception;

    //更新product的productStatus状态
    void updateStatus(String[] ids, int i) throws Exception;

    //通过id查询product
    Product findProductById(String id) throws Exception;

    //更新product
    void updateProduct(Product product) throws Exception;

    //模糊查询,不带分页
    List<Product> findProductNameLike(String productName) throws Exception;

}
