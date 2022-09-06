package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListActiveViolationsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String securityProfileName;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListActiveViolationsRequest)) {
            return false;
        }
        ListActiveViolationsRequest listActiveViolationsRequest = (ListActiveViolationsRequest) obj;
        if ((listActiveViolationsRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getThingName() != null && !listActiveViolationsRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listActiveViolationsRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getSecurityProfileName() != null && !listActiveViolationsRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((listActiveViolationsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getNextToken() != null && !listActiveViolationsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listActiveViolationsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listActiveViolationsRequest.getMaxResults() == null || listActiveViolationsRequest.getMaxResults().equals(getMaxResults());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getMaxResults() != null) {
            i = getMaxResults().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSecurityProfileName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1073.append(getSecurityProfileName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1075.append(getMaxResults());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListActiveViolationsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListActiveViolationsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListActiveViolationsRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public ListActiveViolationsRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
