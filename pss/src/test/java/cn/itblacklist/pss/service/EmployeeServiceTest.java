package cn.itblacklist.pss.service;

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
