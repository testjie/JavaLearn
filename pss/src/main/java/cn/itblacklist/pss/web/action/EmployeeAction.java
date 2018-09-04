package cn.itblacklist.pss.web.action;

import cn.itblacklist.pss.domain.Employee;
import cn.itblacklist.pss.page.PageList;
import cn.itblacklist.pss.query.EmployeeQuery;
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
    // private List<Employee> emps = new ArrayList<>();

    /**
     * 这里已经确定类型了
     */
    private EmployeeQuery baseQuery = new EmployeeQuery();

    private PageList<Employee> pageList = new PageList<>();

    public EmployeeQuery getBaseQuery() {
        return baseQuery;
    }

    public PageList<Employee> getEmps() {
        return pageList;
    }

    @Override
    public String execute() throws Exception{
        pageList = employeeService.findPageByQuery(baseQuery);
        return SUCCESS;
    }

    public PageList<Employee> getPageList() {
        return pageList;
    }


}
