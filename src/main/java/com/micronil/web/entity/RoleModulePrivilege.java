package com.micronil.web.entity;

import javax.persistence.*;

/**
 * Created by apoptoxin on 2018/3/28.
 */

@Entity
@Table(name = "t_role_module_privilege")
public class RoleModulePrivilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rid")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "mpid")
    private ModulePrivilege modulePrivilege;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ModulePrivilege getModulePrivilege() {
        return modulePrivilege;
    }

    public void setModulePrivilege(ModulePrivilege modulePrivilege) {
        this.modulePrivilege = modulePrivilege;
    }
}
