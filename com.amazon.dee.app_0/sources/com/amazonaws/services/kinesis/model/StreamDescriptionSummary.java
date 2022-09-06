package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
public class StreamDescriptionSummary implements Serializable {
    private String encryptionType;
    private List<EnhancedMetrics> enhancedMonitoring = new ArrayList();
    private String keyId;
    private Integer openShardCount;
    private Integer retentionPeriodHours;
    private String streamARN;
    private Date streamCreationTimestamp;
    private String streamName;
    private String streamStatus;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamDescriptionSummary)) {
            return false;
        }
        StreamDescriptionSummary streamDescriptionSummary = (StreamDescriptionSummary) obj;
        if ((streamDescriptionSummary.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (streamDescriptionSummary.getStreamName() != null && !streamDescriptionSummary.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((streamDescriptionSummary.getStreamARN() == null) ^ (getStreamARN() == null)) {
            return false;
        }
        if (streamDescriptionSummary.getStreamARN() != null && !streamDescriptionSummary.getStreamARN().equals(getStreamARN())) {
            return false;
        }
        if ((streamDescriptionSummary.getStreamStatus() == null) ^ (getStreamStatus() == null)) {
            return false;
        }
        if (streamDescriptionSummary.getStreamStatus() != null && !streamDescriptionSummary.getStreamStatus().equals(getStreamStatus())) {
            return false;
        }
        if ((streamDescriptionSummary.getRetentionPeriodHours() == null) ^ (getRetentionPeriodHours() == null)) {
            return false;
        }
        if (streamDescriptionSummary.getRetentionPeriodHours() != null && !streamDescriptionSummary.getRetentionPeriodHours().equals(getRetentionPeriodHours())) {
            return false;
        }
        if ((streamDescriptionSummary.getStreamCreationTimestamp() == null) ^ (getStreamCreationTimestamp() == null)) {
            return false;
        }
        if (streamDescriptionSummary.getStreamCreationTimestamp() != null && !streamDescriptionSummary.getStreamCreationTimestamp().equals(getStreamCreationTimestamp())) {
            return false;
        }
        if ((streamDescriptionSummary.getEnhancedMonitoring() == null) ^ (getEnhancedMonitoring() == null)) {
            return false;
        }
        if (streamDescriptionSummary.getEnhancedMonitoring() != null && !streamDescriptionSummary.getEnhancedMonitoring().equals(getEnhancedMonitoring())) {
            return false;
        }
        if ((streamDescriptionSummary.getEncryptionType() == null) ^ (getEncryptionType() == null)) {
            return false;
        }
        if (streamDescriptionSummary.getEncryptionType() != null && !streamDescriptionSummary.getEncryptionType().equals(getEncryptionType())) {
            return false;
        }
        if ((streamDescriptionSummary.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (streamDescriptionSummary.getKeyId() != null && !streamDescriptionSummary.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((streamDescriptionSummary.getOpenShardCount() == null) ^ (getOpenShardCount() == null)) {
            return false;
        }
        return streamDescriptionSummary.getOpenShardCount() == null || streamDescriptionSummary.getOpenShardCount().equals(getOpenShardCount());
    }

    public String getEncryptionType() {
        return this.encryptionType;
    }

    public List<EnhancedMetrics> getEnhancedMonitoring() {
        return this.enhancedMonitoring;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public Integer getOpenShardCount() {
        return this.openShardCount;
    }

    public Integer getRetentionPeriodHours() {
        return this.retentionPeriodHours;
    }

    public String getStreamARN() {
        return this.streamARN;
    }

    public Date getStreamCreationTimestamp() {
        return this.streamCreationTimestamp;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public String getStreamStatus() {
        return this.streamStatus;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getStreamARN() == null ? 0 : getStreamARN().hashCode())) * 31) + (getStreamStatus() == null ? 0 : getStreamStatus().hashCode())) * 31) + (getRetentionPeriodHours() == null ? 0 : getRetentionPeriodHours().hashCode())) * 31) + (getStreamCreationTimestamp() == null ? 0 : getStreamCreationTimestamp().hashCode())) * 31) + (getEnhancedMonitoring() == null ? 0 : getEnhancedMonitoring().hashCode())) * 31) + (getEncryptionType() == null ? 0 : getEncryptionType().hashCode())) * 31) + (getKeyId() == null ? 0 : getKeyId().hashCode())) * 31;
        if (getOpenShardCount() != null) {
            i = getOpenShardCount().hashCode();
        }
        return hashCode + i;
    }

    public void setEncryptionType(String str) {
        this.encryptionType = str;
    }

    public void setEnhancedMonitoring(Collection<EnhancedMetrics> collection) {
        if (collection == null) {
            this.enhancedMonitoring = null;
        } else {
            this.enhancedMonitoring = new ArrayList(collection);
        }
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public void setOpenShardCount(Integer num) {
        this.openShardCount = num;
    }

    public void setRetentionPeriodHours(Integer num) {
        this.retentionPeriodHours = num;
    }

    public void setStreamARN(String str) {
        this.streamARN = str;
    }

    public void setStreamCreationTimestamp(Date date) {
        this.streamCreationTimestamp = date;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public void setStreamStatus(String str) {
        this.streamStatus = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getStreamARN() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("StreamARN: ");
            outline1073.append(getStreamARN());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getStreamStatus() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("StreamStatus: ");
            outline1074.append(getStreamStatus());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getRetentionPeriodHours() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("RetentionPeriodHours: ");
            outline1075.append(getRetentionPeriodHours());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getStreamCreationTimestamp() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("StreamCreationTimestamp: ");
            outline1076.append(getStreamCreationTimestamp());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getEnhancedMonitoring() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("EnhancedMonitoring: ");
            outline1077.append(getEnhancedMonitoring());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getEncryptionType() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("EncryptionType: ");
            outline1078.append(getEncryptionType());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getKeyId() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("KeyId: ");
            outline1079.append(getKeyId());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getOpenShardCount() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("OpenShardCount: ");
            outline10710.append(getOpenShardCount());
            outline107.append(outline10710.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StreamDescriptionSummary withEncryptionType(String str) {
        this.encryptionType = str;
        return this;
    }

    public StreamDescriptionSummary withEnhancedMonitoring(EnhancedMetrics... enhancedMetricsArr) {
        if (getEnhancedMonitoring() == null) {
            this.enhancedMonitoring = new ArrayList(enhancedMetricsArr.length);
        }
        for (EnhancedMetrics enhancedMetrics : enhancedMetricsArr) {
            this.enhancedMonitoring.add(enhancedMetrics);
        }
        return this;
    }

    public StreamDescriptionSummary withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public StreamDescriptionSummary withOpenShardCount(Integer num) {
        this.openShardCount = num;
        return this;
    }

    public StreamDescriptionSummary withRetentionPeriodHours(Integer num) {
        this.retentionPeriodHours = num;
        return this;
    }

    public StreamDescriptionSummary withStreamARN(String str) {
        this.streamARN = str;
        return this;
    }

    public StreamDescriptionSummary withStreamCreationTimestamp(Date date) {
        this.streamCreationTimestamp = date;
        return this;
    }

    public StreamDescriptionSummary withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public StreamDescriptionSummary withStreamStatus(String str) {
        this.streamStatus = str;
        return this;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
    }

    public void setStreamStatus(StreamStatus streamStatus) {
        this.streamStatus = streamStatus.toString();
    }

    public StreamDescriptionSummary withEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
        return this;
    }

    public StreamDescriptionSummary withStreamStatus(StreamStatus streamStatus) {
        this.streamStatus = streamStatus.toString();
        return this;
    }

    public StreamDescriptionSummary withEnhancedMonitoring(Collection<EnhancedMetrics> collection) {
        setEnhancedMonitoring(collection);
        return this;
    }
}
