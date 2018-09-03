package cn.itblacklist.pss.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

    // 添加、修改、删除后的配置结果试图
    public static final String RELOAD = "reload";

    // 把数据放到map中
    public void putContext(String key, Object value){
        ActionContext.getContext().put(key, value);
    }
}
