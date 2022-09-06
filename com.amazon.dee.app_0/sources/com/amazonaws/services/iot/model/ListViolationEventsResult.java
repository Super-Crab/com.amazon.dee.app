package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListViolationEventsResult implements Serializable {
    private String nextToken;
    private List<ViolationEvent> violationEvents;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListViolationEventsResult)) {
            return false;
        }
        ListViolationEventsResult listViolationEventsResult = (ListViolationEventsResult) obj;
        if ((listViolationEventsResult.getViolationEvents() == null) ^ (getViolationEvents() == null)) {
            return false;
        }
        if (listViolationEventsResult.getViolationEvents() != null && !listViolationEventsResult.getViolationEvents().equals(getViolationEvents())) {
            return false;
        }
        if ((listViolationEventsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listViolationEventsResult.getNextToken() == null || listViolationEventsResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<ViolationEvent> getViolationEvents() {
        return this.violationEvents;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getViolationEvents() == null ? 0 : getViolationEvents().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setViolationEvents(Collection<ViolationEvent> collection) {
        if (collection == null) {
            this.violationEvents = null;
        } else {
            this.violationEvents = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getViolationEvents() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("violationEvents: ");
            outline1072.append(getViolationEvents());
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

    public ListViolationEventsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListViolationEventsResult withViolationEvents(ViolationEvent... violationEventArr) {
        if (getViolationEvents() == null) {
            this.violationEvents = new ArrayList(violationEventArr.length);
        }
        for (ViolationEvent violationEvent : violationEventArr) {
            this.violationEvents.add(violationEvent);
        }
        return this;
    }

    public ListViolationEventsResult withViolationEvents(Collection<ViolationEvent> collection) {
        setViolationEvents(collection);
        return this;
    }
}
