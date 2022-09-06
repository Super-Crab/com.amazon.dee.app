package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListThingTypesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String thingTypeName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingTypesRequest)) {
            return false;
        }
        ListThingTypesRequest listThingTypesRequest = (ListThingTypesRequest) obj;
        if ((listThingTypesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingTypesRequest.getNextToken() != null && !listThingTypesRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingTypesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listThingTypesRequest.getMaxResults() != null && !listThingTypesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listThingTypesRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        return listThingTypesRequest.getThingTypeName() == null || listThingTypesRequest.getThingTypeName().equals(getThingTypeName());
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
        int hashCode = ((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31;
        if (getThingTypeName() != null) {
            i = getThingTypeName().hashCode();
        }
        return hashCode + i;
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
        if (getThingTypeName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1074.append(getThingTypeName());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingTypesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListThingTypesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingTypesRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }
}
