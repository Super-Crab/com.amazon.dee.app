package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListActiveViolationsResult implements Serializable {
    private List<ActiveViolation> activeViolations;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListActiveViolationsResult)) {
            return false;
        }
        ListActiveViolationsResult listActiveViolationsResult = (ListActiveViolationsResult) obj;
        if ((listActiveViolationsResult.getActiveViolations() == null) ^ (getActiveViolations() == null)) {
            return false;
        }
        if (listActiveViolationsResult.getActiveViolations() != null && !listActiveViolationsResult.getActiveViolations().equals(getActiveViolations())) {
            return false;
        }
        if ((listActiveViolationsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listActiveViolationsResult.getNextToken() == null || listActiveViolationsResult.getNextToken().equals(getNextToken());
    }

    public List<ActiveViolation> getActiveViolations() {
        return this.activeViolations;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getActiveViolations() == null ? 0 : getActiveViolations().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setActiveViolations(Collection<ActiveViolation> collection) {
        if (collection == null) {
            this.activeViolations = null;
        } else {
            this.activeViolations = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getActiveViolations() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("activeViolations: ");
            outline1072.append(getActiveViolations());
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

    public ListActiveViolationsResult withActiveViolations(ActiveViolation... activeViolationArr) {
        if (getActiveViolations() == null) {
            this.activeViolations = new ArrayList(activeViolationArr.length);
        }
        for (ActiveViolation activeViolation : activeViolationArr) {
            this.activeViolations.add(activeViolation);
        }
        return this;
    }

    public ListActiveViolationsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListActiveViolationsResult withActiveViolations(Collection<ActiveViolation> collection) {
        setActiveViolations(collection);
        return this;
    }
}
