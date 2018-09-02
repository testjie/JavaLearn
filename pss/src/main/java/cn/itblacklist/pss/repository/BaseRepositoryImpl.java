package cn.itblacklist.pss.repository;

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

    // 设置查询参数
    private void builderJpaParameter(Query query, Object... values) {
        if (values != null) {
            // jpa索引从1开始
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
    }

}
