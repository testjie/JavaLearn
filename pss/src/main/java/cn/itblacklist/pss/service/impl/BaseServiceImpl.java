package cn.itblacklist.pss.service.impl;

import cn.itblacklist.pss.page.PageList;
import cn.itblacklist.pss.query.BaseQuery;
import cn.itblacklist.pss.repository.BaseRepository;
import cn.itblacklist.pss.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, ID> {
    // spring4.x开始直接注入BaseRepository，自动获取对应的子接口
    /**
     * BaseRepository
     * 	问题:BaseRepository它会有多少个子类(很多:EmployeeRepository,DepartmentRepository,...)
     *        我们怎么知道这里注入的是哪一个子类？
     * BaseRepository<T, ID> -> EmployeeRepository<Employee,Long>,DepartmentRepository<Department,Long>,..
     * spring4.x提供了一个新的功能(泛型注入)
     * 		它注入的时候不仅会对应类型(BaseRepository),还会去对应泛型的类型
     * 		如果我们可以确定T这个泛型的类型，我们就可以确定yty的类型
     * 		那么这个T现在是什么类型呢？ -> 它是哪个类型咱们现在不知道 (当我们创建子类的时候就知道了)
     * 			例:EmployeeServiceImpl extends BaseServiceImpl<Employee,Long>
     * 			例:DepartmentServiceImpl extends BaseServiceImpl<Department,Long>
     */
    @Autowired
    protected BaseRepository<T, ID> baseRepository;

    @Override
    @Transactional
    public void save(T t) {
        baseRepository.save(t);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    @Override
    public T get(ID id) {
        return baseRepository.findOne(id);
    }

    @Override
    public List<T> getAll() {
        return baseRepository.findAll();
    }

    @Override
    public List findByJpql(String jpql, Object... values) {
        return baseRepository.findByJpql(jpql, values);
    }

    @Override
    public List findCacheByJpql(String jpql ,Object...values){
        return baseRepository.findCacheByJpql(jpql, values);
    }

    @Override
    public PageList<T> findPageByQuery(BaseQuery baseQuery) {
        return baseRepository.findPageByQuery(baseQuery);
    }
}

