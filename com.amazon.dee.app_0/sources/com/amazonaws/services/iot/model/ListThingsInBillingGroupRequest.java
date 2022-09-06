package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListThingsInBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupName;
    private Integer maxResults;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingsInBillingGroupRequest)) {
            return false;
        }
        ListThingsInBillingGroupRequest listThingsInBillingGroupRequest = (ListThingsInBillingGroupRequest) obj;
        if ((listThingsInBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (listThingsInBillingGroupRequest.getBillingGroupName() != null && !listThingsInBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((listThingsInBillingGroupRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingsInBillingGroupRequest.getNextToken() != null && !listThingsInBillingGroupRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingsInBillingGroupRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listThingsInBillingGroupRequest.getMaxResults() == null || listThingsInBillingGroupRequest.getMaxResults().equals(getMaxResults());
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getMaxResults() != null) {
            i = getMaxResults().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBillingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("billingGroupName: ");
            outline1072.append(getBillingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1074.append(getMaxResults());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingsInBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public ListThingsInBillingGroupRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListThingsInBillingGroupRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
