package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListThingsInBillingGroupResult implements Serializable {
    private String nextToken;
    private List<String> things;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingsInBillingGroupResult)) {
            return false;
        }
        ListThingsInBillingGroupResult listThingsInBillingGroupResult = (ListThingsInBillingGroupResult) obj;
        if ((listThingsInBillingGroupResult.getThings() == null) ^ (getThings() == null)) {
            return false;
        }
        if (listThingsInBillingGroupResult.getThings() != null && !listThingsInBillingGroupResult.getThings().equals(getThings())) {
            return false;
        }
        if ((listThingsInBillingGroupResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingsInBillingGroupResult.getNextToken() == null || listThingsInBillingGroupResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<String> getThings() {
        return this.things;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThings() == null ? 0 : getThings().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setThings(Collection<String> collection) {
        if (collection == null) {
            this.things = null;
        } else {
            this.things = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThings() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("things: ");
            outline1072.append(getThings());
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

    public ListThingsInBillingGroupResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingsInBillingGroupResult withThings(String... strArr) {
        if (getThings() == null) {
            this.things = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.things.add(str);
        }
        return this;
    }

    public ListThingsInBillingGroupResult withThings(Collection<String> collection) {
        setThings(collection);
        return this;
    }
}
