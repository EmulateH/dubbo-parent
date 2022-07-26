package com.itheima.dubbo.service;

import com.itheima.dubbo.User;
import com.itheima.dubbo.UserFace;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Value;

@DubboService//标识在服务的提供方注解
//@DubboService(weight = 200)   // 设置权重
//@DubboService(timeout = 1000)   // 【2提供方】超时时间 -- 接口级别
//@DubboService(methods = {    // 【提供方】 方法级别
//        @Method(name = "getById", timeout = 1800)
//})
//@DubboService(retries = 0)   // 重试次数  0：不重试
//冪等：http请求时，不管调用几次，结果都一样
//非冪等：http请求时，不管调用几次，结果都变化
//GET(查询冪等)  POST(新增非冪等)  PUT(表单修改冪等， set修改操作非冪等)  DELETE(删除冪等)
//@DubboService(methods = {    // 【提供方】 方法级别
//        @Method(name = "add", retries = 0)
//})
//建议：设置重试次数最多不要超过2次，设置0或1
//@DubboService(version = "${dubbo.application.version}")   // 灰度发布-多版本
public class UserFaceImpl implements UserFace {
    @Override
    public String hello(String username) {
        return "Hello Dubbo Username:" + username;
    }

    @Value("${server.port}")
    private Integer port;

    @Override
    public User getById(User user) {
        User user1 = new User(user.getId(), "上海黑马程序员", 7);
        System.out.println("version1.0.0"+port);
        String itName = RpcContext.getContext().getAttachment("itName");
        System.out.println(itName);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return user1;
    }
}
