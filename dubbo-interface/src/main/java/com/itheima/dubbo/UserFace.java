package com.itheima.dubbo;


public interface UserFace {

    /**
     * 永远的helloWorld
     * @param username
     * @return
     */
    public String hello(String username);

    /**
     * 根据条件查询对象
     * @param user
     * @return
     */
    public User getById(User user);
}
