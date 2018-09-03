package cn.itblacklist.pss.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class MainAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String right(){
        return "right";
    }
}
