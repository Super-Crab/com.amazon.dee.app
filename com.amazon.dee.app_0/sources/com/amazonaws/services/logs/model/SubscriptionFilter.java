package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SubscriptionFilter implements Serializable {
    private Long creationTime;
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
        if (obj == null || !(obj instanceof SubscriptionFilter)) {
            return false;
        }
        SubscriptionFilter subscriptionFilter = (SubscriptionFilter) obj;
        if ((subscriptionFilter.getFilterName() == null) ^ (getFilterName() == null)) {
            return false;
        }
        if (subscriptionFilter.getFilterName() != null && !subscriptionFilter.getFilterName().equals(getFilterName())) {
            return false;
        }
        if ((subscriptionFilter.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (subscriptionFilter.getLogGroupName() != null && !subscriptionFilter.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((subscriptionFilter.getFilterPattern() == null) ^ (getFilterPattern() == null)) {
            return false;
        }
        if (subscriptionFilter.getFilterPattern() != null && !subscriptionFilter.getFilterPattern().equals(getFilterPattern())) {
            return false;
        }
        if ((subscriptionFilter.getDestinationArn() == null) ^ (getDestinationArn() == null)) {
            return false;
        }
        if (subscriptionFilter.getDestinationArn() != null && !subscriptionFilter.getDestinationArn().equals(getDestinationArn())) {
            return false;
        }
        if ((subscriptionFilter.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (subscriptionFilter.getRoleArn() != null && !subscriptionFilter.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((subscriptionFilter.getDistribution() == null) ^ (getDistribution() == null)) {
            return false;
        }
        if (subscriptionFilter.getDistribution() != null && !subscriptionFilter.getDistribution().equals(getDistribution())) {
            return false;
        }
        if ((subscriptionFilter.getCreationTime() == null) ^ (getCreationTime() == null)) {
            return false;
        }
        return subscriptionFilter.getCreationTime() == null || subscriptionFilter.getCreationTime().equals(getCreationTime());
    }

    public Long getCreationTime() {
        return this.creationTime;
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
        int hashCode = ((((((((((((getFilterName() == null ? 0 : getFilterName().hashCode()) + 31) * 31) + (getLogGroupName() == null ? 0 : getLogGroupName().hashCode())) * 31) + (getFilterPattern() == null ? 0 : getFilterPattern().hashCode())) * 31) + (getDestinationArn() == null ? 0 : getDestinationArn().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getDistribution() == null ? 0 : getDistribution().hashCode())) * 31;
        if (getCreationTime() != null) {
            i = getCreationTime().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationTime(Long l) {
        this.creationTime = l;
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
        if (getFilterName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("filterName: ");
            outline1072.append(getFilterName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLogGroupName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1073.append(getLogGroupName());
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
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getCreationTime() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("creationTime: ");
            outline1078.append(getCreationTime());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SubscriptionFilter withCreationTime(Long l) {
        this.creationTime = l;
        return this;
    }

    public SubscriptionFilter withDestinationArn(String str) {
        this.destinationArn = str;
        return this;
    }

    public SubscriptionFilter withDistribution(String str) {
        this.distribution = str;
        return this;
    }

    public SubscriptionFilter withFilterName(String str) {
        this.filterName = str;
        return this;
    }

    public SubscriptionFilter withFilterPattern(String str) {
        this.filterPattern = str;
        return this;
    }

    public SubscriptionFilter withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public SubscriptionFilter withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution.toString();
    }

    public SubscriptionFilter withDistribution(Distribution distribution) {
        this.distribution = distribution.toString();
        return this;
    }
}
