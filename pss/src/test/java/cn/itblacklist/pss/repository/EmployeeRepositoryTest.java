package cn.itblacklist.pss.repository;

import cn.itblacklist.pss.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testName() throws Exception{
        System.out.println(employeeRepository);
        System.out.println(employeeRepository.getClass());

        List<Employee> emps = employeeRepository.findAll();
        for (Employee emp : emps){
            System.out.println(emp);
        }
    }
}
