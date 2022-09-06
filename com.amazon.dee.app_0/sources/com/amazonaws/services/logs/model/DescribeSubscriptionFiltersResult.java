package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DescribeSubscriptionFiltersResult implements Serializable {
    private String nextToken;
    private List<SubscriptionFilter> subscriptionFilters;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeSubscriptionFiltersResult)) {
            return false;
        }
        DescribeSubscriptionFiltersResult describeSubscriptionFiltersResult = (DescribeSubscriptionFiltersResult) obj;
        if ((describeSubscriptionFiltersResult.getSubscriptionFilters() == null) ^ (getSubscriptionFilters() == null)) {
            return false;
        }
        if (describeSubscriptionFiltersResult.getSubscriptionFilters() != null && !describeSubscriptionFiltersResult.getSubscriptionFilters().equals(getSubscriptionFilters())) {
            return false;
        }
        if ((describeSubscriptionFiltersResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return describeSubscriptionFiltersResult.getNextToken() == null || describeSubscriptionFiltersResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<SubscriptionFilter> getSubscriptionFilters() {
        return this.subscriptionFilters;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSubscriptionFilters() == null ? 0 : getSubscriptionFilters().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSubscriptionFilters(Collection<SubscriptionFilter> collection) {
        if (collection == null) {
            this.subscriptionFilters = null;
        } else {
            this.subscriptionFilters = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSubscriptionFilters() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("subscriptionFilters: ");
            outline1072.append(getSubscriptionFilters());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeSubscriptionFiltersResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeSubscriptionFiltersResult withSubscriptionFilters(SubscriptionFilter... subscriptionFilterArr) {
        if (getSubscriptionFilters() == null) {
            this.subscriptionFilters = new ArrayList(subscriptionFilterArr.length);
        }
        for (SubscriptionFilter subscriptionFilter : subscriptionFilterArr) {
            this.subscriptionFilters.add(subscriptionFilter);
        }
        return this;
    }

    public DescribeSubscriptionFiltersResult withSubscriptionFilters(Collection<SubscriptionFilter> collection) {
        setSubscriptionFilters(collection);
        return this;
    }
}
