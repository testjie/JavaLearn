package cn.itblacklist.pss.query;

import cn.itblacklist.pss.domain.Employee;
import org.apache.commons.lang3.StringUtils;

public class EmployeeQuery extends BaseQuery {

    private String username;
    private String email;
    private Long deptId;

    /**
     * 调用父类addWhere，拼接jpql
     */
    public void addCondition(){

        if(StringUtils.isNoneBlank(username)){
            super.addWhere("o.username like ?", "%"+username+"%");
        }
        if(StringUtils.isNoneBlank(email)){
            super.addWhere("o.email like ?", "%"+email+"%");
        }
        if(deptId!=null){
            super.addWhere("o.dept.id = ?", "%"+deptId+"%");
        }
    }

    public EmployeeQuery(){
        super(Employee.class);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
