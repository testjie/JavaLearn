package cn.itblacklist.pss.repository;

import cn.itblacklist.pss.page.PageList;
import cn.itblacklist.pss.query.BaseQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/***
 * 持久层基类接口
 *
 * @author snake
 * @NoRepositoryBean: 让SpringDataJpa不要自动去实现它
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 扩展：自己写JPQL
     * @param jpql
     * @param values jpql中？对应的值
     * @return
     */
    List findByJpql(String jpql, Object... values);

    List findCacheByJpql(String cacheJpql, Object... values);

    PageList<T> findPageByQuery(BaseQuery baseQuery);
}
