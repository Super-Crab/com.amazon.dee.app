package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutSubscriptionFilterRequest extends AmazonWebServiceRequest implements Serializable {
    private String destinationArn;
    private String distribution;
    private String filterName;
    private String filterPattern;
    private String logGroupName;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutSubscriptionFilterRequest)) {
            return false;
        }
        PutSubscriptionFilterRequest putSubscriptionFilterRequest = (PutSubscriptionFilterRequest) obj;
        if ((putSubscriptionFilterRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (putSubscriptionFilterRequest.getLogGroupName() != null && !putSubscriptionFilterRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((putSubscriptionFilterRequest.getFilterName() == null) ^ (getFilterName() == null)) {
            return false;
        }
        if (putSubscriptionFilterRequest.getFilterName() != null && !putSubscriptionFilterRequest.getFilterName().equals(getFilterName())) {
            return false;
        }
        if ((putSubscriptionFilterRequest.getFilterPattern() == null) ^ (getFilterPattern() == null)) {
            return false;
        }
        if (putSubscriptionFilterRequest.getFilterPattern() != null && !putSubscriptionFilterRequest.getFilterPattern().equals(getFilterPattern())) {
            return false;
        }
        if ((putSubscriptionFilterRequest.getDestinationArn() == null) ^ (getDestinationArn() == null)) {
            return false;
        }
        if (putSubscriptionFilterRequest.getDestinationArn() != null && !putSubscriptionFilterRequest.getDestinationArn().equals(getDestinationArn())) {
            return false;
        }
        if ((putSubscriptionFilterRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (putSubscriptionFilterRequest.getRoleArn() != null && !putSubscriptionFilterRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((putSubscriptionFilterRequest.getDistribution() == null) ^ (getDistribution() == null)) {
            return false;
        }
        return putSubscriptionFilterRequest.getDistribution() == null || putSubscriptionFilterRequest.getDistribution().equals(getDistribution());
    }

    public String getDestinationArn() {
        return this.destinationArn;
    }

    public String getDistribution() {
        return this.distribution;
    }

    public String getFilterName() {
        return this.filterName;
    }

    public String getFilterPattern() {
        return this.filterPattern;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getFilterName() == null ? 0 : getFilterName().hashCode())) * 31) + (getFilterPattern() == null ? 0 : getFilterPattern().hashCode())) * 31) + (getDestinationArn() == null ? 0 : getDestinationArn().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31;
        if (getDistribution() != null) {
            i = getDistribution().hashCode();
        }
        return hashCode + i;
    }

    public void setDestinationArn(String str) {
        this.destinationArn = str;
    }

    public void setDistribution(String str) {
        this.distribution = str;
    }

    public void setFilterName(String str) {
        this.filterName = str;
    }

    public void setFilterPattern(String str) {
        this.filterPattern = str;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getFilterName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("filterName: ");
            outline1073.append(getFilterName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getFilterPattern() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("filterPattern: ");
            outline1074.append(getFilterPattern());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDestinationArn() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("destinationArn: ");
            outline1075.append(getDestinationArn());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1076.append(getRoleArn());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getDistribution() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("distribution: ");
            outline1077.append(getDistribution());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutSubscriptionFilterRequest withDestinationArn(String str) {
        this.destinationArn = str;
        return this;
    }

    public PutSubscriptionFilterRequest withDistribution(String str) {
        this.distribution = str;
        return this;
    }

    public PutSubscriptionFilterRequest withFilterName(String str) {
        this.filterName = str;
        return this;
    }

    public PutSubscriptionFilterRequest withFilterPattern(String str) {
        this.filterPattern = str;
        return this;
    }

    public PutSubscriptionFilterRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public PutSubscriptionFilterRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution.toString();
    }

    public PutSubscriptionFilterRequest withDistribution(Distribution distribution) {
        this.distribution = distribution.toString();
        return this;
    }
}
