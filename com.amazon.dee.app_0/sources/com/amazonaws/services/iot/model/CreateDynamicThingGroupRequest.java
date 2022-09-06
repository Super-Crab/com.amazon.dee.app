package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class CreateDynamicThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String indexName;
    private String queryString;
    private String queryVersion;
    private List<Tag> tags;
    private String thingGroupName;
    private ThingGroupProperties thingGroupProperties;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateDynamicThingGroupRequest)) {
            return false;
        }
        CreateDynamicThingGroupRequest createDynamicThingGroupRequest = (CreateDynamicThingGroupRequest) obj;
        if ((createDynamicThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (createDynamicThingGroupRequest.getThingGroupName() != null && !createDynamicThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((createDynamicThingGroupRequest.getThingGroupProperties() == null) ^ (getThingGroupProperties() == null)) {
            return false;
        }
        if (createDynamicThingGroupRequest.getThingGroupProperties() != null && !createDynamicThingGroupRequest.getThingGroupProperties().equals(getThingGroupProperties())) {
            return false;
        }
        if ((createDynamicThingGroupRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (createDynamicThingGroupRequest.getIndexName() != null && !createDynamicThingGroupRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((createDynamicThingGroupRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (createDynamicThingGroupRequest.getQueryString() != null && !createDynamicThingGroupRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((createDynamicThingGroupRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        if (createDynamicThingGroupRequest.getQueryVersion() != null && !createDynamicThingGroupRequest.getQueryVersion().equals(getQueryVersion())) {
            return false;
        }
        if ((createDynamicThingGroupRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createDynamicThingGroupRequest.getTags() == null || createDynamicThingGroupRequest.getTags().equals(getTags());
    }

    public String getIndexName() {
        return this.indexName;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public String getQueryVersion() {
        return this.queryVersion;
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
        int hashCode = ((((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupProperties() == null ? 0 : getThingGroupProperties().hashCode())) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getQueryVersion() == null ? 0 : getQueryVersion().hashCode())) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
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
        if (getThingGroupProperties() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingGroupProperties: ");
            outline1073.append(getThingGroupProperties());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getIndexName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("indexName: ");
            outline1074.append(getIndexName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getQueryString() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("queryString: ");
            outline1075.append(getQueryString());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getQueryVersion() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("queryVersion: ");
            outline1076.append(getQueryVersion());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getTags() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("tags: ");
            outline1077.append(getTags());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateDynamicThingGroupRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public CreateDynamicThingGroupRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public CreateDynamicThingGroupRequest withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }

    public CreateDynamicThingGroupRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateDynamicThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public CreateDynamicThingGroupRequest withThingGroupProperties(ThingGroupProperties thingGroupProperties) {
        this.thingGroupProperties = thingGroupProperties;
        return this;
    }

    public CreateDynamicThingGroupRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }
}
