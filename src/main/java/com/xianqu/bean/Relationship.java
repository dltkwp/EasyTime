package com.xianqu.bean;

public class Relationship {
    private Long id;

    private Long uid;

    private Long pid;

    private Long distributorLevelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getDistributorLevelId() {
        return distributorLevelId;
    }

    public void setDistributorLevelId(Long distributorLevelId) {
        this.distributorLevelId = distributorLevelId;
    }
}