package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListPrincipalThingsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String principal;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPrincipalThingsRequest)) {
            return false;
        }
        ListPrincipalThingsRequest listPrincipalThingsRequest = (ListPrincipalThingsRequest) obj;
        if ((listPrincipalThingsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listPrincipalThingsRequest.getNextToken() != null && !listPrincipalThingsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listPrincipalThingsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listPrincipalThingsRequest.getMaxResults() != null && !listPrincipalThingsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listPrincipalThingsRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        return listPrincipalThingsRequest.getPrincipal() == null || listPrincipalThingsRequest.getPrincipal().equals(getPrincipal());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31;
        if (getPrincipal() != null) {
            i = getPrincipal().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setPrincipal(String str) {
        this.principal = str;
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
        if (getPrincipal() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("principal: ");
            outline1074.append(getPrincipal());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListPrincipalThingsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListPrincipalThingsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListPrincipalThingsRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }
}
