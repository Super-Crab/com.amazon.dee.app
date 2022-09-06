package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListBillingGroupsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String namePrefixFilter;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListBillingGroupsRequest)) {
            return false;
        }
        ListBillingGroupsRequest listBillingGroupsRequest = (ListBillingGroupsRequest) obj;
        if ((listBillingGroupsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listBillingGroupsRequest.getNextToken() != null && !listBillingGroupsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listBillingGroupsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listBillingGroupsRequest.getMaxResults() != null && !listBillingGroupsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listBillingGroupsRequest.getNamePrefixFilter() == null) ^ (getNamePrefixFilter() == null)) {
            return false;
        }
        return listBillingGroupsRequest.getNamePrefixFilter() == null || listBillingGroupsRequest.getNamePrefixFilter().equals(getNamePrefixFilter());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNamePrefixFilter() {
        return this.namePrefixFilter;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31;
        if (getNamePrefixFilter() != null) {
            i = getNamePrefixFilter().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNamePrefixFilter(String str) {
        this.namePrefixFilter = str;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
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
        if (getNamePrefixFilter() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("namePrefixFilter: ");
            outline1074.append(getNamePrefixFilter());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListBillingGroupsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListBillingGroupsRequest withNamePrefixFilter(String str) {
        this.namePrefixFilter = str;
        return this;
    }

    public ListBillingGroupsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
