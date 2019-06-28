package com.haocang.pojo;

import java.util.Date;

public class ExcessiveRule {

    private Integer id; // 主键
    private String equipmentId; // 设备id(冗余字段)
    private String equipmentName; // 设备名称(冗余字段)
    private String indicatorsName; // 指标名称
    private Double indicatorsCeiling; // 指标值上限
    private Double indicatorsLower; // 指标值下限
    private Double indicatorsDefault; // 指标超标默认值(暂时使用默认)
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间
    private String createUserId; // 创建人id(预留)
    private String updateUserId; // 更新人id(预留)
    private Integer delFlag; // 删除标记(默认1未删除,0已删除)
    private String remark; // 备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getIndicatorsName() {
        return indicatorsName;
    }

    public void setIndicatorsName(String indicatorsName) {
        this.indicatorsName = indicatorsName;
    }

    public Double getIndicatorsCeiling() {
        return indicatorsCeiling;
    }

    public void setIndicatorsCeiling(Double indicatorsCeiling) {
        this.indicatorsCeiling = indicatorsCeiling;
    }

    public Double getIndicatorsLower() {
        return indicatorsLower;
    }

    public void setIndicatorsLower(Double indicatorsLower) {
        this.indicatorsLower = indicatorsLower;
    }

    public Double getIndicatorsDefault() {
        return indicatorsDefault;
    }

    public void setIndicatorsDefault(Double indicatorsDefault) {
        this.indicatorsDefault = indicatorsDefault;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
