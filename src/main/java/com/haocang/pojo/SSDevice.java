package com.haocang.pojo;

import java.util.Date;

public class SSDevice {
    private Integer id;

    private String rtuid;

    private Float ss;

    private Date recdate;

    private Float voltage;

    private Date dbdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRtuid() {
        return rtuid;
    }

    public void setRtuid(String rtuid) {
        this.rtuid = rtuid == null ? null : rtuid.trim();
    }

    public Float getSs() {
        return ss;
    }

    public void setSs(Float ss) {
        this.ss = ss;
    }

    public Date getRecdate() {
        return recdate;
    }

    public void setRecdate(Date recdate) {
        this.recdate = recdate;
    }

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public Date getDbdate() {
        return dbdate;
    }

    public void setDbdate(Date dbdate) {
        this.dbdate = dbdate;
    }
}