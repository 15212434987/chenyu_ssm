package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    //查询所有product,带条件,分页
    public List<Product> findAll(Integer pageNum, Integer pageSize,String productName) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        return productDao.findAll(productName);
    }

    //添加product
    public void addProduct(Product product) throws Exception {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        product.setId(uuid);
        productDao.addProduct(product);
    }

    //批量删除product
    public void delProduct(String[] ids) throws Exception {
        for (String id : ids) {
            productDao.delProduct(id);
        }
    }

    //更新product的productStatus状态
    public void updateStatus(String[] ids, int statu) throws Exception {
        for (String id : ids) {
            productDao.updateStatus(id,statu);
        }
    }

    //通过id查询product
    public Product findProductById(String id) throws Exception {
        return productDao.findProductById(id);
    }

    //更新product
    public void updateProduct(Product product) throws Exception {
        productDao.updateProduct(product);
    }

    //模糊查询,不带分页
    public List<Product> findProductNameLike(String productName) throws Exception {
        return productDao.findProductNameLike(productName);
    }
}
