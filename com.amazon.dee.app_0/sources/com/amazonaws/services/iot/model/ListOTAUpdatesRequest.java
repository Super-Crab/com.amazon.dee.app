package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListOTAUpdatesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String otaUpdateStatus;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListOTAUpdatesRequest)) {
            return false;
        }
        ListOTAUpdatesRequest listOTAUpdatesRequest = (ListOTAUpdatesRequest) obj;
        if ((listOTAUpdatesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listOTAUpdatesRequest.getMaxResults() != null && !listOTAUpdatesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listOTAUpdatesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listOTAUpdatesRequest.getNextToken() != null && !listOTAUpdatesRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listOTAUpdatesRequest.getOtaUpdateStatus() == null) ^ (getOtaUpdateStatus() == null)) {
            return false;
        }
        return listOTAUpdatesRequest.getOtaUpdateStatus() == null || listOTAUpdatesRequest.getOtaUpdateStatus().equals(getOtaUpdateStatus());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getOtaUpdateStatus() {
        return this.otaUpdateStatus;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getMaxResults() == null ? 0 : getMaxResults().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getOtaUpdateStatus() != null) {
            i = getOtaUpdateStatus().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setOtaUpdateStatus(String str) {
        this.otaUpdateStatus = str;
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
        if (getOtaUpdateStatus() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("otaUpdateStatus: ");
            outline1074.append(getOtaUpdateStatus());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListOTAUpdatesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListOTAUpdatesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListOTAUpdatesRequest withOtaUpdateStatus(String str) {
        this.otaUpdateStatus = str;
        return this;
    }

    public void setOtaUpdateStatus(OTAUpdateStatus oTAUpdateStatus) {
        this.otaUpdateStatus = oTAUpdateStatus.toString();
    }

    public ListOTAUpdatesRequest withOtaUpdateStatus(OTAUpdateStatus oTAUpdateStatus) {
        this.otaUpdateStatus = oTAUpdateStatus.toString();
        return this;
    }
}
