package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class TagResourceRequest extends AmazonWebServiceRequest implements Serializable {
    private String resourceArn;
    private List<Tag> tags;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TagResourceRequest)) {
            return false;
        }
        TagResourceRequest tagResourceRequest = (TagResourceRequest) obj;
        if ((tagResourceRequest.getResourceArn() == null) ^ (getResourceArn() == null)) {
            return false;
        }
        if (tagResourceRequest.getResourceArn() != null && !tagResourceRequest.getResourceArn().equals(getResourceArn())) {
            return false;
        }
        if ((tagResourceRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return tagResourceRequest.getTags() == null || tagResourceRequest.getTags().equals(getTags());
    }

    public String getResourceArn() {
        return this.resourceArn;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getResourceArn() == null ? 0 : getResourceArn().hashCode()) + 31) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setResourceArn(String str) {
        this.resourceArn = str;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
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
        if (getTags() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1073.append(getTags());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TagResourceRequest withResourceArn(String str) {
        this.resourceArn = str;
        return this;
    }

    public TagResourceRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public TagResourceRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }
}
