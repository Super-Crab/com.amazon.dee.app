package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListV2LoggingLevelsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String targetType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListV2LoggingLevelsRequest)) {
            return false;
        }
        ListV2LoggingLevelsRequest listV2LoggingLevelsRequest = (ListV2LoggingLevelsRequest) obj;
        if ((listV2LoggingLevelsRequest.getTargetType() == null) ^ (getTargetType() == null)) {
            return false;
        }
        if (listV2LoggingLevelsRequest.getTargetType() != null && !listV2LoggingLevelsRequest.getTargetType().equals(getTargetType())) {
            return false;
        }
        if ((listV2LoggingLevelsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listV2LoggingLevelsRequest.getNextToken() != null && !listV2LoggingLevelsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listV2LoggingLevelsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listV2LoggingLevelsRequest.getMaxResults() == null || listV2LoggingLevelsRequest.getMaxResults().equals(getMaxResults());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getTargetType() {
        return this.targetType;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getTargetType() == null ? 0 : getTargetType().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
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

    public void setTargetType(String str) {
        this.targetType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTargetType() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("targetType: ");
            outline1072.append(getTargetType());
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

    public ListV2LoggingLevelsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListV2LoggingLevelsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListV2LoggingLevelsRequest withTargetType(String str) {
        this.targetType = str;
        return this;
    }

    public void setTargetType(LogTargetType logTargetType) {
        this.targetType = logTargetType.toString();
    }

    public ListV2LoggingLevelsRequest withTargetType(LogTargetType logTargetType) {
        this.targetType = logTargetType.toString();
        return this;
    }
}
