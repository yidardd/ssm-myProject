package com.txn.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * dw_role
 * @author 
 */
public class DwRole implements Serializable {
    private Long id;

    /**
     * 上级id
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态（0正常，1失效）
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除
     */
    private Byte isDeleted;

    /**
     * 创建时间
     */
    private Date crtTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}