package cn.itblacklist.pss.query;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * 我们把所有关系查询公共的一些东西放在这里面
 * 我们有很多domain，几乎所有domain都需要做CRUD
 * 更涉及到他们是需要做高级查询和分页的
 * 分页条件都是一样的-》抽取分页的条件到父类
 */
public abstract class BaseQuery {

    private int currentPage = 1;
    private int pageSize = 10;

    /**
     *  在BaseQuery中进行JPQL的拼接
     * @return
     */

    // 一条查询总条数
    private StringBuffer countJpql;
    // 一条查询所有数
    private StringBuffer resultJpql;
    // 准备一个集合，把所有条件的参数都存起来
    private List<Object> paramsList = new ArrayList<>();

    /**
     * BaseQuery是一个公共的Query，Employee对象不合适
     * 子类才能确定类型
     */
    public BaseQuery(Class entityClass){
        countJpql = new StringBuffer("select count(o) from "+entityClass.getName()+" o");
        resultJpql = new StringBuffer("select o from "+entityClass.getName()+" o");
    }

    /**
     * 我们已经决定在BaseQuery中来拼接咱们的where条件
     * 1. 咱们的条件在子类，在子类拼接就好了，为什么要在父类拼接呢？
     * 2. 父类只是准备一个方法，来解决and和where的问题
     *      我们所有的JPQL，在拼接条件的手，（第一个条件前面是where，后面的条件都是and）
     * 我们准备一个方法，来进行这个拼接
     * @return
     */


    /**
     * 用于拼接jsql（主题部分已经存在）
     *  这个方法主要是子类来调用
     *  jsqlWhere = username like ? / age between ? and ?
     * @param jpqlWhere
     * @param params
     */
    public void addWhere(String jpqlWhere, Object... params){
        /**
         * 第一次调用方法的时候：
         *  如果paramsList中没有值，就使用where
         *  如果paramsList中有值，就使用and
         */
        if(paramsList.size() > 0){
            countJpql.append(" and ").append(jpqlWhere);
            resultJpql.append(" and ").append(jpqlWhere);
        }else{
            countJpql.append(" where ").append(jpqlWhere);
            resultJpql.append(" where ").append(jpqlWhere);
        }
        //addAll：把一个集合中的所有值放到另一个集合中
        paramsList.addAll(Arrays.asList(params));
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    // addwhere 确保不会重复引用where
    private boolean flag = true;

    public void builderWhere(){
        if (flag){
            addCondition();
            flag = false;
        }
    }

    // 父类要使用子类方法，就使用钩子形式
    protected abstract void addCondition();

    // 这里我们只为它们接受了get -> 对于这些值，我们只需要获取，不需要设置
    public String getCountJpql() {
        builderWhere();
        return countJpql.toString();
    }

    public String getResultJpql() {
        builderWhere();
        return resultJpql.toString();
    }

    public List<Object> getParamsList() {
        builderWhere();
        return paramsList;
    }
}
