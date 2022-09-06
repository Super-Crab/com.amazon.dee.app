package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class GetLogEventsResult implements Serializable {
    private List<OutputLogEvent> events;
    private String nextBackwardToken;
    private String nextForwardToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetLogEventsResult)) {
            return false;
        }
        GetLogEventsResult getLogEventsResult = (GetLogEventsResult) obj;
        if ((getLogEventsResult.getEvents() == null) ^ (getEvents() == null)) {
            return false;
        }
        if (getLogEventsResult.getEvents() != null && !getLogEventsResult.getEvents().equals(getEvents())) {
            return false;
        }
        if ((getLogEventsResult.getNextForwardToken() == null) ^ (getNextForwardToken() == null)) {
            return false;
        }
        if (getLogEventsResult.getNextForwardToken() != null && !getLogEventsResult.getNextForwardToken().equals(getNextForwardToken())) {
            return false;
        }
        if ((getLogEventsResult.getNextBackwardToken() == null) ^ (getNextBackwardToken() == null)) {
            return false;
        }
        return getLogEventsResult.getNextBackwardToken() == null || getLogEventsResult.getNextBackwardToken().equals(getNextBackwardToken());
    }

    public List<OutputLogEvent> getEvents() {
        return this.events;
    }

    public String getNextBackwardToken() {
        return this.nextBackwardToken;
    }

    public String getNextForwardToken() {
        return this.nextForwardToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getEvents() == null ? 0 : getEvents().hashCode()) + 31) * 31) + (getNextForwardToken() == null ? 0 : getNextForwardToken().hashCode())) * 31;
        if (getNextBackwardToken() != null) {
            i = getNextBackwardToken().hashCode();
        }
        return hashCode + i;
    }

    public void setEvents(Collection<OutputLogEvent> collection) {
        if (collection == null) {
            this.events = null;
        } else {
            this.events = new ArrayList(collection);
        }
    }

    public void setNextBackwardToken(String str) {
        this.nextBackwardToken = str;
    }

    public void setNextForwardToken(String str) {
        this.nextForwardToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEvents() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("events: ");
            outline1072.append(getEvents());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextForwardToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextForwardToken: ");
            outline1073.append(getNextForwardToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextBackwardToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextBackwardToken: ");
            outline1074.append(getNextBackwardToken());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetLogEventsResult withEvents(OutputLogEvent... outputLogEventArr) {
        if (getEvents() == null) {
            this.events = new ArrayList(outputLogEventArr.length);
        }
        for (OutputLogEvent outputLogEvent : outputLogEventArr) {
            this.events.add(outputLogEvent);
        }
        return this;
    }

    public GetLogEventsResult withNextBackwardToken(String str) {
        this.nextBackwardToken = str;
        return this;
    }

    public GetLogEventsResult withNextForwardToken(String str) {
        this.nextForwardToken = str;
        return this;
    }

    public GetLogEventsResult withEvents(Collection<OutputLogEvent> collection) {
        setEvents(collection);
        return this;
    }
}
