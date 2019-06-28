package com.haocang.pojo;

import java.util.Date;

public class RainDevice {
    private Integer id;

    private String rtuid;

    private Float min10pre;

    private Float sumpre;

    private Date recdate;

    private Date dbdate;

    private Integer screenings;

    private String rainlevel;

    private Float voltage;

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

    public Float getMin10pre() {
        return min10pre;
    }

    public void setMin10pre(Float min10pre) {
        this.min10pre = min10pre;
    }

    public Float getSumpre() {
        return sumpre;
    }

    public void setSumpre(Float sumpre) {
        this.sumpre = sumpre;
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

    public Integer getScreenings() {
        return screenings;
    }

    public void setScreenings(Integer screenings) {
        this.screenings = screenings;
    }

    public String getRainlevel() {
        return rainlevel;
    }

    public void setRainlevel(String rainlevel) {
        this.rainlevel = rainlevel == null ? null : rainlevel.trim();
    }

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }
}