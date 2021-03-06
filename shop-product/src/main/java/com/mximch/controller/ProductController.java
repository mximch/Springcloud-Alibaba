package com.mximch.controller;

import com.alibaba.fastjson.JSON;
import com.mximch.domain.Product;
import com.mximch.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    //商品胸袭查询
    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid){
        log.info("接下来要进行{}商品喜喜查询",pid);

        Product product = productService.findByPid(pid);
        log.info("商品信息查询成功，内容为{}", JSON.toJSONString(product));
        return product;
    }
}
