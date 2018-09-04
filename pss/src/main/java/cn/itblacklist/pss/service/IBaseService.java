package cn.itblacklist.pss.service;

import cn.itblacklist.pss.page.PageList;
import cn.itblacklist.pss.query.BaseQuery;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T, ID extends Serializable> {
    // 添加或者修改
    void save(T t);

    // 删除
    void delete(ID id);

    T get(ID id);

    // 查询所有
    List<T> getAll();

    List findByJpql(final String jpql, Object... values);

    List findCacheByJpql(final String jpql, Object... values);

    PageList<T> findPageByQuery(BaseQuery baseQuery);
}

