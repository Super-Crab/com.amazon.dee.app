package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
public class StreamDescription implements Serializable {
    private String encryptionType;
    private Boolean hasMoreShards;
    private String keyId;
    private Integer retentionPeriodHours;
    private String streamARN;
    private Date streamCreationTimestamp;
    private String streamName;
    private String streamStatus;
    private List<Shard> shards = new ArrayList();
    private List<EnhancedMetrics> enhancedMonitoring = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamDescription)) {
            return false;
        }
        StreamDescription streamDescription = (StreamDescription) obj;
        if ((streamDescription.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (streamDescription.getStreamName() != null && !streamDescription.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((streamDescription.getStreamARN() == null) ^ (getStreamARN() == null)) {
            return false;
        }
        if (streamDescription.getStreamARN() != null && !streamDescription.getStreamARN().equals(getStreamARN())) {
            return false;
        }
        if ((streamDescription.getStreamStatus() == null) ^ (getStreamStatus() == null)) {
            return false;
        }
        if (streamDescription.getStreamStatus() != null && !streamDescription.getStreamStatus().equals(getStreamStatus())) {
            return false;
        }
        if ((streamDescription.getShards() == null) ^ (getShards() == null)) {
            return false;
        }
        if (streamDescription.getShards() != null && !streamDescription.getShards().equals(getShards())) {
            return false;
        }
        if ((streamDescription.getHasMoreShards() == null) ^ (getHasMoreShards() == null)) {
            return false;
        }
        if (streamDescription.getHasMoreShards() != null && !streamDescription.getHasMoreShards().equals(getHasMoreShards())) {
            return false;
        }
        if ((streamDescription.getRetentionPeriodHours() == null) ^ (getRetentionPeriodHours() == null)) {
            return false;
        }
        if (streamDescription.getRetentionPeriodHours() != null && !streamDescription.getRetentionPeriodHours().equals(getRetentionPeriodHours())) {
            return false;
        }
        if ((streamDescription.getStreamCreationTimestamp() == null) ^ (getStreamCreationTimestamp() == null)) {
            return false;
        }
        if (streamDescription.getStreamCreationTimestamp() != null && !streamDescription.getStreamCreationTimestamp().equals(getStreamCreationTimestamp())) {
            return false;
        }
        if ((streamDescription.getEnhancedMonitoring() == null) ^ (getEnhancedMonitoring() == null)) {
            return false;
        }
        if (streamDescription.getEnhancedMonitoring() != null && !streamDescription.getEnhancedMonitoring().equals(getEnhancedMonitoring())) {
            return false;
        }
        if ((streamDescription.getEncryptionType() == null) ^ (getEncryptionType() == null)) {
            return false;
        }
        if (streamDescription.getEncryptionType() != null && !streamDescription.getEncryptionType().equals(getEncryptionType())) {
            return false;
        }
        if ((streamDescription.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        return streamDescription.getKeyId() == null || streamDescription.getKeyId().equals(getKeyId());
    }

    public String getEncryptionType() {
        return this.encryptionType;
    }

    public List<EnhancedMetrics> getEnhancedMonitoring() {
        return this.enhancedMonitoring;
    }

    public Boolean getHasMoreShards() {
        return this.hasMoreShards;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public Integer getRetentionPeriodHours() {
        return this.retentionPeriodHours;
    }

    public List<Shard> getShards() {
        return this.shards;
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
        int hashCode = ((((((((((((((((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getStreamARN() == null ? 0 : getStreamARN().hashCode())) * 31) + (getStreamStatus() == null ? 0 : getStreamStatus().hashCode())) * 31) + (getShards() == null ? 0 : getShards().hashCode())) * 31) + (getHasMoreShards() == null ? 0 : getHasMoreShards().hashCode())) * 31) + (getRetentionPeriodHours() == null ? 0 : getRetentionPeriodHours().hashCode())) * 31) + (getStreamCreationTimestamp() == null ? 0 : getStreamCreationTimestamp().hashCode())) * 31) + (getEnhancedMonitoring() == null ? 0 : getEnhancedMonitoring().hashCode())) * 31) + (getEncryptionType() == null ? 0 : getEncryptionType().hashCode())) * 31;
        if (getKeyId() != null) {
            i = getKeyId().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isHasMoreShards() {
        return this.hasMoreShards;
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

    public void setHasMoreShards(Boolean bool) {
        this.hasMoreShards = bool;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public void setRetentionPeriodHours(Integer num) {
        this.retentionPeriodHours = num;
    }

    public void setShards(Collection<Shard> collection) {
        if (collection == null) {
            this.shards = null;
        } else {
            this.shards = new ArrayList(collection);
        }
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
        if (getShards() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Shards: ");
            outline1075.append(getShards());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getHasMoreShards() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("HasMoreShards: ");
            outline1076.append(getHasMoreShards());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getRetentionPeriodHours() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("RetentionPeriodHours: ");
            outline1077.append(getRetentionPeriodHours());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getStreamCreationTimestamp() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("StreamCreationTimestamp: ");
            outline1078.append(getStreamCreationTimestamp());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getEnhancedMonitoring() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("EnhancedMonitoring: ");
            outline1079.append(getEnhancedMonitoring());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getEncryptionType() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("EncryptionType: ");
            outline10710.append(getEncryptionType());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getKeyId() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("KeyId: ");
            outline10711.append(getKeyId());
            outline107.append(outline10711.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StreamDescription withEncryptionType(String str) {
        this.encryptionType = str;
        return this;
    }

    public StreamDescription withEnhancedMonitoring(EnhancedMetrics... enhancedMetricsArr) {
        if (getEnhancedMonitoring() == null) {
            this.enhancedMonitoring = new ArrayList(enhancedMetricsArr.length);
        }
        for (EnhancedMetrics enhancedMetrics : enhancedMetricsArr) {
            this.enhancedMonitoring.add(enhancedMetrics);
        }
        return this;
    }

    public StreamDescription withHasMoreShards(Boolean bool) {
        this.hasMoreShards = bool;
        return this;
    }

    public StreamDescription withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public StreamDescription withRetentionPeriodHours(Integer num) {
        this.retentionPeriodHours = num;
        return this;
    }

    public StreamDescription withShards(Shard... shardArr) {
        if (getShards() == null) {
            this.shards = new ArrayList(shardArr.length);
        }
        for (Shard shard : shardArr) {
            this.shards.add(shard);
        }
        return this;
    }

    public StreamDescription withStreamARN(String str) {
        this.streamARN = str;
        return this;
    }

    public StreamDescription withStreamCreationTimestamp(Date date) {
        this.streamCreationTimestamp = date;
        return this;
    }

    public StreamDescription withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public StreamDescription withStreamStatus(String str) {
        this.streamStatus = str;
        return this;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
    }

    public void setStreamStatus(StreamStatus streamStatus) {
        this.streamStatus = streamStatus.toString();
    }

    public StreamDescription withEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
        return this;
    }

    public StreamDescription withStreamStatus(StreamStatus streamStatus) {
        this.streamStatus = streamStatus.toString();
        return this;
    }

    public StreamDescription withEnhancedMonitoring(Collection<EnhancedMetrics> collection) {
        setEnhancedMonitoring(collection);
        return this;
    }

    public StreamDescription withShards(Collection<Shard> collection) {
        setShards(collection);
        return this;
    }
}
