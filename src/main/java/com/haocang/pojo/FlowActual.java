package com.haocang.pojo;

import java.util.Date;

public class FlowActual {
    private Integer id;

    private String rtuid;

    private Float voltage;

    private String flowrateid;

    private Float flowrate;

    private String waterlevelid;

    private Float waterlevel;

    private String vellocityid;

    private Float velocity;

    private Double totalflow;

    private String rtuname;

    private String manufactuer;

    private String remark;

    private Date colldatetime;

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

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public String getFlowrateid() {
        return flowrateid;
    }

    public void setFlowrateid(String flowrateid) {
        this.flowrateid = flowrateid == null ? null : flowrateid.trim();
    }

    public Float getFlowrate() {
        return flowrate;
    }

    public void setFlowrate(Float flowrate) {
        this.flowrate = flowrate;
    }

    public String getWaterlevelid() {
        return waterlevelid;
    }

    public void setWaterlevelid(String waterlevelid) {
        this.waterlevelid = waterlevelid == null ? null : waterlevelid.trim();
    }

    public Float getWaterlevel() {
        return waterlevel;
    }

    public void setWaterlevel(Float waterlevel) {
        this.waterlevel = waterlevel;
    }

    public String getVellocityid() {
        return vellocityid;
    }

    public void setVellocityid(String vellocityid) {
        this.vellocityid = vellocityid == null ? null : vellocityid.trim();
    }

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
    }

    public Double getTotalflow() {
        return totalflow;
    }

    public void setTotalflow(Double totalflow) {
        this.totalflow = totalflow;
    }

    public String getRtuname() {
        return rtuname;
    }

    public void setRtuname(String rtuname) {
        this.rtuname = rtuname == null ? null : rtuname.trim();
    }

    public String getManufactuer() {
        return manufactuer;
    }

    public void setManufactuer(String manufactuer) {
        this.manufactuer = manufactuer == null ? null : manufactuer.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getColldatetime() {
        return colldatetime;
    }

    public void setColldatetime(Date colldatetime) {
        this.colldatetime = colldatetime;
    }

    public Date getDbdate() {
        return dbdate;
    }

    public void setDbdate(Date dbdate) {
        this.dbdate = dbdate;
    }
}