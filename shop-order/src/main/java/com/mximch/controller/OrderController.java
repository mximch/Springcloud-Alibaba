package com.mximch.controller;

import com.alibaba.fastjson.JSON;
import com.mximch.dao.OrderDao;
import com.mximch.domain.Order;
import com.mximch.domain.Product;
import com.mximch.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    //下单
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接受到{}号商品的下单请求，接下来调用商品微服务查询此商品信息", pid);

        //调用商品微服务，查询商品信息

        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        ServiceInstance serviceInstance = instances.get(0);

        Product product = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + pid, Product.class);

        log.info("查询到{}号商品的信息，内容是:{}", pid, JSON.toJSONString(product));

        //下单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);


        orderService.createOrder(order);

        log.info("创建顶但成功，订单信息为{}", JSON.toJSONString(order));

        return order;
    }

//    //下单
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接受到{}号商品的下单请求，接下来调用商品微服务查询此商品信息",pid);
//
//        //调用商品微服务，查询商品信息
//
//        //问题
//        //1 一旦服务提供者的地址信息拜年话了，我们就不得不去修改服务
//        //2 一旦服务提供者做了集群，服务调用者一方无法实现负载均衡
//        //3 一旦微服务变得越来越多，如何来管理这个服务清单就变成了问题
//        Product product = restTemplate.getForObject("http://localhost:8081/product/"+pid,Product.class);
//
//        log.info("查询到{}号商品的信息，内容是:{}",pid, JSON.toJSONString(product));
//
//        //下单
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//
//
//        orderService.createOrder(order);
//
//        log.info("创建顶但成功，订单信息为{}",JSON.toJSONString(order));
//
//        return order;
//    }

}
