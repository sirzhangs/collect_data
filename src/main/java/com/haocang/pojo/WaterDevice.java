package com.haocang.pojo;

import java.util.Date;

public class WaterDevice {
    private Long id;

    private String deviceid;

    private Float pd;

    private Float hd;

    private Date recdate;

    private Date dbdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public Float getPd() {
        return pd;
    }

    public void setPd(Float pd) {
        this.pd = pd;
    }

    public Float getHd() {
        return hd;
    }

    public void setHd(Float hd) {
        this.hd = hd;
    }

    public Date getRecdate() {
        return recdate;
    }

    public void setRecdate(Date recdate) {
        this.recdate = recdate;
    }

    public Date getDbdate() {
        return dbdate;
    }

    public void setDbdate(Date dbdate) {
        this.dbdate = dbdate;
    }
}