package com.haocang.pojo;

import java.util.Date;

public class IntegrationStation {
    private Integer id;

    private String deviceid;

    private Float ph;

    private Float do2;

    private Float wt;

    private Float cond;

    private Float tub;

    private Float tp;

    private Float nh3n;

    private Float kmno4;

    private Date recdate;

    private Date dbdate;

    private Float voltage;

    private String cn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public Float getPh() {
        return ph;
    }

    public void setPh(Float ph) {
        this.ph = ph;
    }

    public Float getDo2() {
        return do2;
    }

    public void setDo2(Float do2) {
        this.do2 = do2;
    }

    public Float getWt() {
        return wt;
    }

    public void setWt(Float wt) {
        this.wt = wt;
    }

    public Float getCond() {
        return cond;
    }

    public void setCond(Float cond) {
        this.cond = cond;
    }

    public Float getTub() {
        return tub;
    }

    public void setTub(Float tub) {
        this.tub = tub;
    }

    public Float getTp() {
        return tp;
    }

    public void setTp(Float tp) {
        this.tp = tp;
    }

    public Float getNh3n() {
        return nh3n;
    }

    public void setNh3n(Float nh3n) {
        this.nh3n = nh3n;
    }

    public Float getKmno4() {
        return kmno4;
    }

    public void setKmno4(Float kmno4) {
        this.kmno4 = kmno4;
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

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn == null ? null : cn.trim();
    }
}