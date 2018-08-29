package cn.itblacklist.dao.impl;

import java.util.List;
import cn.itblacklist.bean.User;
import cn.itblacklist.dao.BaseDao;
import cn.itblacklist.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public int save(User user) {
        return insert(user);
    }

    @Override
    public int update(User user) {
        return update(user);
    }

    @Override
    public List<User> queryAll() {
        return null;
    }

    @Override
    public User queryUserById() {
        return null;
    }
}
