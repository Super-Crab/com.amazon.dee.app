package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListThingsRequest extends AmazonWebServiceRequest implements Serializable {
    private String attributeName;
    private String attributeValue;
    private Integer maxResults;
    private String nextToken;
    private String thingTypeName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingsRequest)) {
            return false;
        }
        ListThingsRequest listThingsRequest = (ListThingsRequest) obj;
        if ((listThingsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingsRequest.getNextToken() != null && !listThingsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listThingsRequest.getMaxResults() != null && !listThingsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listThingsRequest.getAttributeName() == null) ^ (getAttributeName() == null)) {
            return false;
        }
        if (listThingsRequest.getAttributeName() != null && !listThingsRequest.getAttributeName().equals(getAttributeName())) {
            return false;
        }
        if ((listThingsRequest.getAttributeValue() == null) ^ (getAttributeValue() == null)) {
            return false;
        }
        if (listThingsRequest.getAttributeValue() != null && !listThingsRequest.getAttributeValue().equals(getAttributeValue())) {
            return false;
        }
        if ((listThingsRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        return listThingsRequest.getThingTypeName() == null || listThingsRequest.getThingTypeName().equals(getThingTypeName());
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public String getAttributeValue() {
        return this.attributeValue;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getAttributeName() == null ? 0 : getAttributeName().hashCode())) * 31) + (getAttributeValue() == null ? 0 : getAttributeValue().hashCode())) * 31;
        if (getThingTypeName() != null) {
            i = getThingTypeName().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributeName(String str) {
        this.attributeName = str;
    }

    public void setAttributeValue(String str) {
        this.attributeValue = str;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getNextToken() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1072.append(getNextToken());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1073.append(getMaxResults());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAttributeName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("attributeName: ");
            outline1074.append(getAttributeName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAttributeValue() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("attributeValue: ");
            outline1075.append(getAttributeValue());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getThingTypeName() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1076.append(getThingTypeName());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingsRequest withAttributeName(String str) {
        this.attributeName = str;
        return this;
    }

    public ListThingsRequest withAttributeValue(String str) {
        this.attributeValue = str;
        return this;
    }

    public ListThingsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListThingsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingsRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }
}
