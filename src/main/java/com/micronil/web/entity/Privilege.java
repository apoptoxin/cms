package com.micronil.web.entity;

import javax.persistence.*;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Entity
@Table(name = "t_privilege")
public class Privilege {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "privilege_name", nullable = false)
    private String privilegeName;

    @Column(name = "privilege_code", nullable = false, unique = true)
    private String privilegeCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }
}
