package cn.itblacklist.pss.web.action;

import cn.itblacklist.pss.domain.Employee;
import cn.itblacklist.pss.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class EmployeeAction extends BaseAction{

    @Autowired
    private IEmployeeService employeeService;

    // 一般当前类使用List
    private List<Employee> emps = new ArrayList<>();

    public List<Employee> getEmps() {
        return emps;
    }

    @Override
    public String execute() throws Exception{
        emps = employeeService.getAll();
        return SUCCESS;
    }
}
