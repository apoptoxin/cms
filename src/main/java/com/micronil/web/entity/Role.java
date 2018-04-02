package com.micronil.web.entity;

import javax.persistence.*;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "cur_key")
    private String key;

    @Column(name = "parent_role_id")
    private Long parentRoleId;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "reverse_key")
    private String reverseKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(Long parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getReverseKey() {
        return reverseKey;
    }

    public void setReverseKey(String reverseKey) {
        this.reverseKey = reverseKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
