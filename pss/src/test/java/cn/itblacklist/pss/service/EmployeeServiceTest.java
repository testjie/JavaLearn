package cn.itblacklist.pss.service;

import cn.itblacklist.pss.domain.Employee;
import cn.itblacklist.pss.page.PageList;
import cn.itblacklist.pss.query.EmployeeQuery;
import cn.itblacklist.pss.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest{
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;


    /**
     * 测试先行
     * 1.我能不能查询到数据
     * 2. 我穿了条件，能不能进行过滤
     * 3. 我穿的分页，能不能分页
     * a. 没有传参他能帮我拼接一条JPQL吗？ √
     * b. 如果穿的条件，JPQL后面有where吗？（如果第一个条件是where，后面是and）
     * c. 如果我穿的分页有误（如每条数据为负数），它会自动纠正吗？
     * @throws Exception
     */

    @Test
    public void testFindPage() throws Exception{
        EmployeeQuery baseQuery = new EmployeeQuery();
//        baseQuery.setUsername("snake");
//        baseQuery.setEmail("2");
//        baseQuery.setDeptId(new Long(2));
        baseQuery.setCurrentPage(Integer.MIN_VALUE);
        PageList<Employee> pageList = employeeService.findPageByQuery(baseQuery);
        System.out.println(pageList);
        for (Employee emp: pageList.getResult()) {
            System.out.println(emp);
        }
    }

    @Test
    public void list() throws Exception {
        System.out.println(employeeRepository);
        System.out.println(employeeRepository.getClass());
        System.out.println(employeeRepository.findAll().size());
    }

    @Test
    public void list2() throws Exception {
        System.out.println(employeeService);
        System.out.println(employeeService.getClass());
        System.out.println(employeeService.getAll().size());
    }

}
