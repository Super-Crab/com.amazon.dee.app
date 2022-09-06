package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListIndicesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListIndicesRequest)) {
            return false;
        }
        ListIndicesRequest listIndicesRequest = (ListIndicesRequest) obj;
        if ((listIndicesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listIndicesRequest.getNextToken() != null && !listIndicesRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listIndicesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listIndicesRequest.getMaxResults() == null || listIndicesRequest.getMaxResults().equals(getMaxResults());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31;
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
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListIndicesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListIndicesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
