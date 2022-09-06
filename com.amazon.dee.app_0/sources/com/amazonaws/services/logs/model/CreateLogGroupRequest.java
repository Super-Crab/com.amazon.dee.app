package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class CreateLogGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String kmsKeyId;
    private String logGroupName;
    private Map<String, String> tags;

    public CreateLogGroupRequest() {
    }

    public CreateLogGroupRequest addtagsEntry(String str, String str2) {
        if (this.tags == null) {
            this.tags = new HashMap();
        }
        if (!this.tags.containsKey(str)) {
            this.tags.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public CreateLogGroupRequest cleartagsEntries() {
        this.tags = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateLogGroupRequest)) {
            return false;
        }
        CreateLogGroupRequest createLogGroupRequest = (CreateLogGroupRequest) obj;
        if ((createLogGroupRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (createLogGroupRequest.getLogGroupName() != null && !createLogGroupRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((createLogGroupRequest.getKmsKeyId() == null) ^ (getKmsKeyId() == null)) {
            return false;
        }
        if (createLogGroupRequest.getKmsKeyId() != null && !createLogGroupRequest.getKmsKeyId().equals(getKmsKeyId())) {
            return false;
        }
        if ((createLogGroupRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createLogGroupRequest.getTags() == null || createLogGroupRequest.getTags().equals(getTags());
    }

    public String getKmsKeyId() {
        return this.kmsKeyId;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getKmsKeyId() == null ? 0 : getKmsKeyId().hashCode())) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setKmsKeyId(String str) {
        this.kmsKeyId = str;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setTags(Map<String, String> map) {
        this.tags = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getKmsKeyId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("kmsKeyId: ");
            outline1073.append(getKmsKeyId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTags() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1074.append(getTags());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateLogGroupRequest withKmsKeyId(String str) {
        this.kmsKeyId = str;
        return this;
    }

    public CreateLogGroupRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public CreateLogGroupRequest withTags(Map<String, String> map) {
        this.tags = map;
        return this;
    }

    public CreateLogGroupRequest(String str) {
        setLogGroupName(str);
    }
}
