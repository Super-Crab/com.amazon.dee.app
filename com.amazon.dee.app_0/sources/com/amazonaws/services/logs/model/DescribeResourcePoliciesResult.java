package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DescribeResourcePoliciesResult implements Serializable {
    private String nextToken;
    private List<ResourcePolicy> resourcePolicies;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeResourcePoliciesResult)) {
            return false;
        }
        DescribeResourcePoliciesResult describeResourcePoliciesResult = (DescribeResourcePoliciesResult) obj;
        if ((describeResourcePoliciesResult.getResourcePolicies() == null) ^ (getResourcePolicies() == null)) {
            return false;
        }
        if (describeResourcePoliciesResult.getResourcePolicies() != null && !describeResourcePoliciesResult.getResourcePolicies().equals(getResourcePolicies())) {
            return false;
        }
        if ((describeResourcePoliciesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return describeResourcePoliciesResult.getNextToken() == null || describeResourcePoliciesResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<ResourcePolicy> getResourcePolicies() {
        return this.resourcePolicies;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getResourcePolicies() == null ? 0 : getResourcePolicies().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setResourcePolicies(Collection<ResourcePolicy> collection) {
        if (collection == null) {
            this.resourcePolicies = null;
        } else {
            this.resourcePolicies = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getResourcePolicies() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("resourcePolicies: ");
            outline1072.append(getResourcePolicies());
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

    public DescribeResourcePoliciesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeResourcePoliciesResult withResourcePolicies(ResourcePolicy... resourcePolicyArr) {
        if (getResourcePolicies() == null) {
            this.resourcePolicies = new ArrayList(resourcePolicyArr.length);
        }
        for (ResourcePolicy resourcePolicy : resourcePolicyArr) {
            this.resourcePolicies.add(resourcePolicy);
        }
        return this;
    }

    public DescribeResourcePoliciesResult withResourcePolicies(Collection<ResourcePolicy> collection) {
        setResourcePolicies(collection);
        return this;
    }
}
