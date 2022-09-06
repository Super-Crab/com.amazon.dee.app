package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class LogGroup implements Serializable {
    private String arn;
    private Long creationTime;
    private String kmsKeyId;
    private String logGroupName;
    private Integer metricFilterCount;
    private Integer retentionInDays;
    private Long storedBytes;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LogGroup)) {
            return false;
        }
        LogGroup logGroup = (LogGroup) obj;
        if ((logGroup.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (logGroup.getLogGroupName() != null && !logGroup.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((logGroup.getCreationTime() == null) ^ (getCreationTime() == null)) {
            return false;
        }
        if (logGroup.getCreationTime() != null && !logGroup.getCreationTime().equals(getCreationTime())) {
            return false;
        }
        if ((logGroup.getRetentionInDays() == null) ^ (getRetentionInDays() == null)) {
            return false;
        }
        if (logGroup.getRetentionInDays() != null && !logGroup.getRetentionInDays().equals(getRetentionInDays())) {
            return false;
        }
        if ((logGroup.getMetricFilterCount() == null) ^ (getMetricFilterCount() == null)) {
            return false;
        }
        if (logGroup.getMetricFilterCount() != null && !logGroup.getMetricFilterCount().equals(getMetricFilterCount())) {
            return false;
        }
        if ((logGroup.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        if (logGroup.getArn() != null && !logGroup.getArn().equals(getArn())) {
            return false;
        }
        if ((logGroup.getStoredBytes() == null) ^ (getStoredBytes() == null)) {
            return false;
        }
        if (logGroup.getStoredBytes() != null && !logGroup.getStoredBytes().equals(getStoredBytes())) {
            return false;
        }
        if ((logGroup.getKmsKeyId() == null) ^ (getKmsKeyId() == null)) {
            return false;
        }
        return logGroup.getKmsKeyId() == null || logGroup.getKmsKeyId().equals(getKmsKeyId());
    }

    public String getArn() {
        return this.arn;
    }

    public Long getCreationTime() {
        return this.creationTime;
    }

    public String getKmsKeyId() {
        return this.kmsKeyId;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public Integer getMetricFilterCount() {
        return this.metricFilterCount;
    }

    public Integer getRetentionInDays() {
        return this.retentionInDays;
    }

    public Long getStoredBytes() {
        return this.storedBytes;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getCreationTime() == null ? 0 : getCreationTime().hashCode())) * 31) + (getRetentionInDays() == null ? 0 : getRetentionInDays().hashCode())) * 31) + (getMetricFilterCount() == null ? 0 : getMetricFilterCount().hashCode())) * 31) + (getArn() == null ? 0 : getArn().hashCode())) * 31) + (getStoredBytes() == null ? 0 : getStoredBytes().hashCode())) * 31;
        if (getKmsKeyId() != null) {
            i = getKmsKeyId().hashCode();
        }
        return hashCode + i;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public void setCreationTime(Long l) {
        this.creationTime = l;
    }

    public void setKmsKeyId(String str) {
        this.kmsKeyId = str;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setMetricFilterCount(Integer num) {
        this.metricFilterCount = num;
    }

    public void setRetentionInDays(Integer num) {
        this.retentionInDays = num;
    }

    public void setStoredBytes(Long l) {
        this.storedBytes = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCreationTime() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("creationTime: ");
            outline1073.append(getCreationTime());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRetentionInDays() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("retentionInDays: ");
            outline1074.append(getRetentionInDays());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMetricFilterCount() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("metricFilterCount: ");
            outline1075.append(getMetricFilterCount());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getArn() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("arn: ");
            outline1076.append(getArn());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getStoredBytes() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("storedBytes: ");
            outline1077.append(getStoredBytes());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getKmsKeyId() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("kmsKeyId: ");
            outline1078.append(getKmsKeyId());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public LogGroup withArn(String str) {
        this.arn = str;
        return this;
    }

    public LogGroup withCreationTime(Long l) {
        this.creationTime = l;
        return this;
    }

    public LogGroup withKmsKeyId(String str) {
        this.kmsKeyId = str;
        return this;
    }

    public LogGroup withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public LogGroup withMetricFilterCount(Integer num) {
        this.metricFilterCount = num;
        return this;
    }

    public LogGroup withRetentionInDays(Integer num) {
        this.retentionInDays = num;
        return this;
    }

    public LogGroup withStoredBytes(Long l) {
        this.storedBytes = l;
        return this;
    }
}
