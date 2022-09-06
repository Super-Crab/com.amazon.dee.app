package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class StartStreamEncryptionRequest extends AmazonWebServiceRequest implements Serializable {
    private String encryptionType;
    private String keyId;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartStreamEncryptionRequest)) {
            return false;
        }
        StartStreamEncryptionRequest startStreamEncryptionRequest = (StartStreamEncryptionRequest) obj;
        if ((startStreamEncryptionRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (startStreamEncryptionRequest.getStreamName() != null && !startStreamEncryptionRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((startStreamEncryptionRequest.getEncryptionType() == null) ^ (getEncryptionType() == null)) {
            return false;
        }
        if (startStreamEncryptionRequest.getEncryptionType() != null && !startStreamEncryptionRequest.getEncryptionType().equals(getEncryptionType())) {
            return false;
        }
        if ((startStreamEncryptionRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        return startStreamEncryptionRequest.getKeyId() == null || startStreamEncryptionRequest.getKeyId().equals(getKeyId());
    }

    public String getEncryptionType() {
        return this.encryptionType;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getEncryptionType() == null ? 0 : getEncryptionType().hashCode())) * 31;
        if (getKeyId() != null) {
            i = getKeyId().hashCode();
        }
        return hashCode + i;
    }

    public void setEncryptionType(String str) {
        this.encryptionType = str;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getEncryptionType() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("EncryptionType: ");
            outline1073.append(getEncryptionType());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getKeyId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("KeyId: ");
            outline1074.append(getKeyId());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StartStreamEncryptionRequest withEncryptionType(String str) {
        this.encryptionType = str;
        return this;
    }

    public StartStreamEncryptionRequest withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public StartStreamEncryptionRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
    }

    public StartStreamEncryptionRequest withEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType.toString();
        return this;
    }
}
