package cn.itblacklist.pss.repository;

import cn.itblacklist.pss.domain.Employee;
import cn.itblacklist.pss.page.PageList;
import cn.itblacklist.pss.query.BaseQuery;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * 持久层基类接口实现
 *
 * @author snake
 *
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, Serializable>
        implements BaseRepository<T, Serializable> {

    private final EntityManager entityManager;

    // 父类没有不带参数的构造方法，这里手动构造父类
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List findByJpql(String jpql, Object... values) {
        Query query = entityManager.createQuery(jpql);
        builderJpaParameter(query, values);
        return query.getResultList();
    }

    @Override
    public List findCacheByJpql(String cacheJpql, Object... values) {
        // 实现查询缓存核心代码setHint("org.hibernate.cacheable", true);
        // public static final java.lang.String HINT_CACHEABLE = "org.hibernate.cacheable";
        Query query = entityManager.createQuery(cacheJpql).setHint(QueryHints.HINT_CACHEABLE, true);
        builderJpaParameter(query, values);
        return query.getResultList();
    }

    /**
     *
     * @param baseQuery
     * @return
     */
    @Override
    public PageList<T> findPageByQuery(BaseQuery baseQuery) {
        System.out.println(baseQuery.getCountJpql());
        System.out.println(baseQuery.getResultJpql());
        System.out.println(baseQuery.getParamsList());

//         1. 查询总条数
        Query query = entityManager.createQuery(baseQuery.getCountJpql());
//         2. 把Query中的问题值加上去
//              注意：由于接受的是可变参数（我们不直接传List，而需要把它变成数组传递）
        builderJpaParameter(query, baseQuery.getParamsList().toArray());
//         1.3 执行方法
//         hibernate4之后，把count返回的值变成了Long
        Long totalCount = (Long) query.getSingleResult();
//        2. 如果总条数为0，说明没有拿到数据，返回一个空的pageList
        if (totalCount <= 0){
            return new PageList<>();
        }
//        3. 如果总条数>0， 我们就去哪当前页的数据
        PageList<T> pageList = new PageList<>(baseQuery.getCurrentPage(), baseQuery.getPageSize(), totalCount.intValue());
//        3.1 新建query对象
        query = entityManager.createQuery(baseQuery.getResultJpql());
//        3.2 设置query参数
        builderJpaParameter(query, baseQuery.getParamsList().toArray());
        /**
         * JPQL设置分页
         *  query.setFirstResult()
         *  query.setMaxResults()
         */
        int firstResult = (pageList.getCurrentPage()-1)*pageList.getPageSize();
        int maxResults = pageList.getPageSize();
//        jpq中规定setFirstResult不能为负数或0
        query.setFirstResult(firstResult).setMaxResults(maxResults);
//        3.3 执行这条sql
        List<Employee> result = query.getResultList();
//        3.4 把值放到PageList中
        pageList.setResult(result);

        return pageList;
    }

    // 设置查询参数
    private void builderJpaParameter(Query query, Object... values) {
        if (values != null) {
//             jpa索引从1开始
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
    }

}
