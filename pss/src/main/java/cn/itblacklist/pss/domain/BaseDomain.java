package cn.itblacklist.pss.domain;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//在JPA里面就表示是父类，不持久化到表
public class BaseDomain {
    @Id
    @GeneratedValue()
    protected Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
