package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductDao {

    //根据id查询product
    Product findProductById(String id) throws Exception;

    //查询所有商品信息,带条件
    List<Product> findAll(String productName) throws Exception;

    //添加商品信息
    void addProduct(Product product) throws Exception;

    //删除product
    void delProduct(String id) throws Exception;

    //更新productStatus
    void updateStatus(@Param("id") String id,@Param("statu") int statu) throws Exception;

    //更新product
    void updateProduct(Product product) throws Exception;


    List<Product> findProductNameLike(String productName) throws Exception;
}
