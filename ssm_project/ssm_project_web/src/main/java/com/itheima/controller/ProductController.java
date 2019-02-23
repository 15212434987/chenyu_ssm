package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    //查询所有产品,带条件,分页查询
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize",required = false,defaultValue = "5")Integer pageSize,
                          @RequestParam(name = "productName",required = false,defaultValue = "")String productName,
                          Model model) throws Exception{
        List<Product> productList = productService.findAll(pageNum,pageSize,productName);
        PageInfo pageInfo = new PageInfo(productList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("productName",productName);
        return "product-list";
    }

    //添加产品product
    @RequestMapping("/addProduct")
    public String addProduct(Product product) throws Exception{
        productService.addProduct(product);
        return "redirect:/product/findAll";
    }

    //修改产品状态
    @RequestMapping("/closeStatus")
    public String closeStatus(String ids) throws Exception{
        String[] productIds = ids.split(",");
        productService.updateStatus(productIds,0);
        return "forward:/product/findAll";
    }

    @RequestMapping("/openStatus")
    public String openStatus(String ids) throws Exception{
        String[] productIds = ids.split(",");
        productService.updateStatus(productIds,1);
        return "forward:/product/findAll";
    }

    //删除
    @RequestMapping("/delProduct")
    public String delProduct(String ids) throws Exception{
        String[] productIds = ids.split(",");
        productService.delProduct(productIds);
        return "forward:/product/findAll";
    }

    //数据回显
    @RequestMapping("/findProductById")
    public String findProductById(String id,Model model) throws Exception{
        Product product = productService.findProductById(id);
        model.addAttribute("product",product);
        return "product-update";
    }

    //修改产品信息
    @RequestMapping("/updateProduct")
    public String updateProduct(Product product) throws Exception{
        productService.updateProduct(product);
        return "forward:/product/findAll";
    }

    //模糊查询
    @RequestMapping("/findProductNameLike")
    public String findProductNameLike(String productName,Model model) throws Exception{
        if (productName != null && !productName.equals("")){
            productName = "%"+productName+"%";
        }else {
            return "forward:/product/findAll";
        }
        List<Product> list = productService.findProductNameLike(productName);
        model.addAttribute("productList",list);
        return "product-list";
    }
}
