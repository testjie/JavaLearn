package cn.itblacklist.dao;

import cn.itblacklist.bean.User;

import java.util.List;

public interface UserDao {

    // 插入用户
    public int save(User user);

    // 修改用户
    public int update(User user);

    // 查询所有用户
    public List<User> queryAll();

    // 根据用户id查询用户
    public User queryUserById();
}
