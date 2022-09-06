package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeDestinationsRequest extends AmazonWebServiceRequest implements Serializable {
    private String destinationNamePrefix;
    private Integer limit;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeDestinationsRequest)) {
            return false;
        }
        DescribeDestinationsRequest describeDestinationsRequest = (DescribeDestinationsRequest) obj;
        if ((describeDestinationsRequest.getDestinationNamePrefix() == null) ^ (getDestinationNamePrefix() == null)) {
            return false;
        }
        if (describeDestinationsRequest.getDestinationNamePrefix() != null && !describeDestinationsRequest.getDestinationNamePrefix().equals(getDestinationNamePrefix())) {
            return false;
        }
        if ((describeDestinationsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (describeDestinationsRequest.getNextToken() != null && !describeDestinationsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((describeDestinationsRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return describeDestinationsRequest.getLimit() == null || describeDestinationsRequest.getLimit().equals(getLimit());
    }

    public String getDestinationNamePrefix() {
        return this.destinationNamePrefix;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getDestinationNamePrefix() == null ? 0 : getDestinationNamePrefix().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public void setDestinationNamePrefix(String str) {
        this.destinationNamePrefix = str;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDestinationNamePrefix() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("DestinationNamePrefix: ");
            outline1072.append(getDestinationNamePrefix());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("limit: ");
            outline1074.append(getLimit());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeDestinationsRequest withDestinationNamePrefix(String str) {
        this.destinationNamePrefix = str;
        return this;
    }

    public DescribeDestinationsRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public DescribeDestinationsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
