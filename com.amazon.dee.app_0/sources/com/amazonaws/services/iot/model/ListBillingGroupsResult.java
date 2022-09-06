package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListBillingGroupsResult implements Serializable {
    private List<GroupNameAndArn> billingGroups;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListBillingGroupsResult)) {
            return false;
        }
        ListBillingGroupsResult listBillingGroupsResult = (ListBillingGroupsResult) obj;
        if ((listBillingGroupsResult.getBillingGroups() == null) ^ (getBillingGroups() == null)) {
            return false;
        }
        if (listBillingGroupsResult.getBillingGroups() != null && !listBillingGroupsResult.getBillingGroups().equals(getBillingGroups())) {
            return false;
        }
        if ((listBillingGroupsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listBillingGroupsResult.getNextToken() == null || listBillingGroupsResult.getNextToken().equals(getNextToken());
    }

    public List<GroupNameAndArn> getBillingGroups() {
        return this.billingGroups;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getBillingGroups() == null ? 0 : getBillingGroups().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingGroups(Collection<GroupNameAndArn> collection) {
        if (collection == null) {
            this.billingGroups = null;
        } else {
            this.billingGroups = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBillingGroups() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("billingGroups: ");
            outline1072.append(getBillingGroups());
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

    public ListBillingGroupsResult withBillingGroups(GroupNameAndArn... groupNameAndArnArr) {
        if (getBillingGroups() == null) {
            this.billingGroups = new ArrayList(groupNameAndArnArr.length);
        }
        for (GroupNameAndArn groupNameAndArn : groupNameAndArnArr) {
            this.billingGroups.add(groupNameAndArn);
        }
        return this;
    }

    public ListBillingGroupsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListBillingGroupsResult withBillingGroups(Collection<GroupNameAndArn> collection) {
        setBillingGroups(collection);
        return this;
    }
}
