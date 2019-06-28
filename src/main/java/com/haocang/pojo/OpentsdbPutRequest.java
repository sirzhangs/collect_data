package com.haocang.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OpentsdbPutRequest implements Serializable {

    private String metric; // 指标
    private long timestamp; // 时间戳
    private Double value; // 指标值
    private Map<String, String> tags = new HashMap<>(); // 标记 键值对

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
