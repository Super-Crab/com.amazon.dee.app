package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class UntagResourceRequest extends AmazonWebServiceRequest implements Serializable {
    private String resourceArn;
    private List<String> tagKeys;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UntagResourceRequest)) {
            return false;
        }
        UntagResourceRequest untagResourceRequest = (UntagResourceRequest) obj;
        if ((untagResourceRequest.getResourceArn() == null) ^ (getResourceArn() == null)) {
            return false;
        }
        if (untagResourceRequest.getResourceArn() != null && !untagResourceRequest.getResourceArn().equals(getResourceArn())) {
            return false;
        }
        if ((untagResourceRequest.getTagKeys() == null) ^ (getTagKeys() == null)) {
            return false;
        }
        return untagResourceRequest.getTagKeys() == null || untagResourceRequest.getTagKeys().equals(getTagKeys());
    }

    public String getResourceArn() {
        return this.resourceArn;
    }

    public List<String> getTagKeys() {
        return this.tagKeys;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getResourceArn() == null ? 0 : getResourceArn().hashCode()) + 31) * 31;
        if (getTagKeys() != null) {
            i = getTagKeys().hashCode();
        }
        return hashCode + i;
    }

    public void setResourceArn(String str) {
        this.resourceArn = str;
    }

    public void setTagKeys(Collection<String> collection) {
        if (collection == null) {
            this.tagKeys = null;
        } else {
            this.tagKeys = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getResourceArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("resourceArn: ");
            outline1072.append(getResourceArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTagKeys() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("tagKeys: ");
            outline1073.append(getTagKeys());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UntagResourceRequest withResourceArn(String str) {
        this.resourceArn = str;
        return this;
    }

    public UntagResourceRequest withTagKeys(String... strArr) {
        if (getTagKeys() == null) {
            this.tagKeys = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.tagKeys.add(str);
        }
        return this;
    }

    public UntagResourceRequest withTagKeys(Collection<String> collection) {
        setTagKeys(collection);
        return this;
    }
}
