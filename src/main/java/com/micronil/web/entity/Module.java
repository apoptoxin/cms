package com.micronil.web.entity;

import javax.persistence.*;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Entity
@Table(name = "t_module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "module_name", nullable = false)
    private String moduleName;

    @Column(name = "cur_key")
    private String key;

    @Column(name = "parent_module_id")
    private Long parentModuleId;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "reverse_key")
    private String reverseKey;

    @Column(name = "url", nullable = false)
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getParentModuleId() {
        return parentModuleId;
    }

    public void setParentModuleId(Long parentModuleId) {
        this.parentModuleId = parentModuleId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
