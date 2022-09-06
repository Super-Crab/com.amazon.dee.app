package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class CreateThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String parentGroupName;
    private List<Tag> tags;
    private String thingGroupName;
    private ThingGroupProperties thingGroupProperties;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingGroupRequest)) {
            return false;
        }
        CreateThingGroupRequest createThingGroupRequest = (CreateThingGroupRequest) obj;
        if ((createThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (createThingGroupRequest.getThingGroupName() != null && !createThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((createThingGroupRequest.getParentGroupName() == null) ^ (getParentGroupName() == null)) {
            return false;
        }
        if (createThingGroupRequest.getParentGroupName() != null && !createThingGroupRequest.getParentGroupName().equals(getParentGroupName())) {
            return false;
        }
        if ((createThingGroupRequest.getThingGroupProperties() == null) ^ (getThingGroupProperties() == null)) {
            return false;
        }
        if (createThingGroupRequest.getThingGroupProperties() != null && !createThingGroupRequest.getThingGroupProperties().equals(getThingGroupProperties())) {
            return false;
        }
        if ((createThingGroupRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createThingGroupRequest.getTags() == null || createThingGroupRequest.getTags().equals(getTags());
    }

    public String getParentGroupName() {
        return this.parentGroupName;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public ThingGroupProperties getThingGroupProperties() {
        return this.thingGroupProperties;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getParentGroupName() == null ? 0 : getParentGroupName().hashCode())) * 31) + (getThingGroupProperties() == null ? 0 : getThingGroupProperties().hashCode())) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setParentGroupName(String str) {
        this.parentGroupName = str;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public void setThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1072.append(getThingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getParentGroupName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("parentGroupName: ");
            outline1073.append(getParentGroupName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingGroupProperties() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingGroupProperties: ");
            outline1074.append(getThingGroupProperties());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTags() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1075.append(getTags());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateThingGroupRequest withParentGroupName(String str) {
        this.parentGroupName = str;
        return this;
    }

    public CreateThingGroupRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public CreateThingGroupRequest withThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
        return this;
    }

    public CreateThingGroupRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }
}
