package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListStreamsRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private Integer maxResults;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListStreamsRequest)) {
            return false;
        }
        ListStreamsRequest listStreamsRequest = (ListStreamsRequest) obj;
        if ((listStreamsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listStreamsRequest.getMaxResults() != null && !listStreamsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listStreamsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listStreamsRequest.getNextToken() != null && !listStreamsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listStreamsRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listStreamsRequest.getAscendingOrder() == null || listStreamsRequest.getAscendingOrder().equals(getAscendingOrder());
    }

    public Boolean getAscendingOrder() {
        return this.ascendingOrder;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getMaxResults() == null ? 0 : getMaxResults().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getAscendingOrder() != null) {
            i = getAscendingOrder().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isAscendingOrder() {
        return this.ascendingOrder;
    }

    public void setAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getMaxResults() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1072.append(getMaxResults());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAscendingOrder() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ascendingOrder: ");
            outline1074.append(getAscendingOrder());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListStreamsRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public ListStreamsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListStreamsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
