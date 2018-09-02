package cn.itblacklist.pss.repository;

import cn.itblacklist.pss.domain.Employee;

/**
 * T:代表你要操作的Domain类型
 * Serializable：你的主键类型
 * @author snake
 */
public interface EmployeeRepository extends BaseRepository<Employee, Long> {
}
