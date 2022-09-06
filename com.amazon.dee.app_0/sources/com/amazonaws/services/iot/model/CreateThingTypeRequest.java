package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class CreateThingTypeRequest extends AmazonWebServiceRequest implements Serializable {
    private List<Tag> tags;
    private String thingTypeName;
    private ThingTypeProperties thingTypeProperties;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingTypeRequest)) {
            return false;
        }
        CreateThingTypeRequest createThingTypeRequest = (CreateThingTypeRequest) obj;
        if ((createThingTypeRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (createThingTypeRequest.getThingTypeName() != null && !createThingTypeRequest.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((createThingTypeRequest.getThingTypeProperties() == null) ^ (getThingTypeProperties() == null)) {
            return false;
        }
        if (createThingTypeRequest.getThingTypeProperties() != null && !createThingTypeRequest.getThingTypeProperties().equals(getThingTypeProperties())) {
            return false;
        }
        if ((createThingTypeRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createThingTypeRequest.getTags() == null || createThingTypeRequest.getTags().equals(getTags());
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public ThingTypeProperties getThingTypeProperties() {
        return this.thingTypeProperties;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getThingTypeProperties() == null ? 0 : getThingTypeProperties().hashCode())) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public void setThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingTypeName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1072.append(getThingTypeName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingTypeProperties() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingTypeProperties: ");
            outline1073.append(getThingTypeProperties());
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

    public CreateThingTypeRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateThingTypeRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public CreateThingTypeRequest withThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
        return this;
    }

    public CreateThingTypeRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }
}
