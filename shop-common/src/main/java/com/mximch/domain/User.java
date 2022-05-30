package com.mximch.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//用户
@Entity(name = "shop_user") //实体类和数据表的对应关系
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //数据库自增
    private Integer uid; //主键
    private String username; //用户名
    private String password; //密码
    private String telephone; //手机号
}
