package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class UntagLogGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String logGroupName;
    private List<String> tags;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UntagLogGroupRequest)) {
            return false;
        }
        UntagLogGroupRequest untagLogGroupRequest = (UntagLogGroupRequest) obj;
        if ((untagLogGroupRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (untagLogGroupRequest.getLogGroupName() != null && !untagLogGroupRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((untagLogGroupRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return untagLogGroupRequest.getTags() == null || untagLogGroupRequest.getTags().equals(getTags());
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setTags(Collection<String> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTags() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1073.append(getTags());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UntagLogGroupRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public UntagLogGroupRequest withTags(String... strArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.tags.add(str);
        }
        return this;
    }

    public UntagLogGroupRequest withTags(Collection<String> collection) {
        setTags(collection);
        return this;
    }
}
