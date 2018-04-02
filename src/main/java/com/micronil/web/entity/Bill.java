package com.micronil.web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by apoptoxin on 2018/3/29.
 */

//@Temporal(TemporalType.TIMESTAMP)
//@Column(updatable = false)
//@org.hibernate.annotations.CreationTimestamp
//private Date CREATETIME;
//@Column(name="UPDATETIME")
//@org.hibernate.annotations.UpdateTimestamp
//@Temporal(TemporalType.TIMESTAMP)
//private Date UPDATETIME;
@Entity
@Table(name = "t_bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "tax_value", nullable = false)
    private Float taxValue;

    @Column(name = "create_time",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "update_time",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "create_user")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Float taxValue) {
        this.taxValue = taxValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
