package com.micronil.web.entity;

import javax.persistence.*;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Entity
@Table(name = "t_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)//如果用户不存在了,可以将这个表中所有与该用户相关的数据删除掉,但是Role表中不能删除
    @JoinColumn(name = "uid")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rid")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
