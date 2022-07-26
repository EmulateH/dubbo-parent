package com.itheima.dubbo.web;

import com.itheima.dubbo.User;
import com.itheima.dubbo.UserFace;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    //@DubboReference  // 远程服务调用注解
    //@DubboReference(check = false )  // 启动时设置不检查服务提供方是否存在
    //@DubboReference(cluster ="failfast" )  // 容错机制failfast 快速失败值，调用一次
    @DubboReference(loadbalance = "roundrobin")  // 负载均衡策略修改 roundrobin:轮询
    //@DubboReference(timeout = 1900)   // 【3消费方】 超时时间
    //@DubboReference(methods = {
    //        @Method(name = "getById",timeout = 1500)
    //})   // 【3消费方】 超时时间 - 方法级别
    //@DubboReference(version = "1.0.1")
    UserFace userFace;

    @GetMapping("/hello")
    public String hello(String username) {
        return userFace.hello(username);
    }

    @GetMapping("/getById")
    public User getById(Long id) {
        User user = new User();
        user.setId(id);

        //隐式传参
        RpcContext.getContext().setAttachment("itName","上海黑马程序员");

        return userFace.getById(user);
    }
}
