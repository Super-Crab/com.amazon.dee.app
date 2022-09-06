package com.amazonaws.services.mobileanalytics.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Deprecated
/* loaded from: classes13.dex */
public class PutEventsRequest extends AmazonWebServiceRequest implements Serializable {
    private String clientContext;
    private String clientContextEncoding;
    private List<Event> events;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutEventsRequest)) {
            return false;
        }
        PutEventsRequest putEventsRequest = (PutEventsRequest) obj;
        if ((putEventsRequest.getEvents() == null) ^ (getEvents() == null)) {
            return false;
        }
        if (putEventsRequest.getEvents() != null && !putEventsRequest.getEvents().equals(getEvents())) {
            return false;
        }
        if ((putEventsRequest.getClientContext() == null) ^ (getClientContext() == null)) {
            return false;
        }
        if (putEventsRequest.getClientContext() != null && !putEventsRequest.getClientContext().equals(getClientContext())) {
            return false;
        }
        if ((putEventsRequest.getClientContextEncoding() == null) ^ (getClientContextEncoding() == null)) {
            return false;
        }
        return putEventsRequest.getClientContextEncoding() == null || putEventsRequest.getClientContextEncoding().equals(getClientContextEncoding());
    }

    public String getClientContext() {
        return this.clientContext;
    }

    public String getClientContextEncoding() {
        return this.clientContextEncoding;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getEvents() == null ? 0 : getEvents().hashCode()) + 31) * 31) + (getClientContext() == null ? 0 : getClientContext().hashCode())) * 31;
        if (getClientContextEncoding() != null) {
            i = getClientContextEncoding().hashCode();
        }
        return hashCode + i;
    }

    public void setClientContext(String str) {
        this.clientContext = str;
    }

    public void setClientContextEncoding(String str) {
        this.clientContextEncoding = str;
    }

    public void setEvents(Collection<Event> collection) {
        if (collection == null) {
            this.events = null;
        } else {
            this.events = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEvents() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("events: ");
            outline1072.append(getEvents());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getClientContext() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("clientContext: ");
            outline1073.append(getClientContext());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getClientContextEncoding() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("clientContextEncoding: ");
            outline1074.append(getClientContextEncoding());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutEventsRequest withClientContext(String str) {
        this.clientContext = str;
        return this;
    }

    public PutEventsRequest withClientContextEncoding(String str) {
        this.clientContextEncoding = str;
        return this;
    }

    public PutEventsRequest withEvents(Event... eventArr) {
        if (getEvents() == null) {
            this.events = new ArrayList(eventArr.length);
        }
        for (Event event : eventArr) {
            this.events.add(event);
        }
        return this;
    }

    public PutEventsRequest withEvents(Collection<Event> collection) {
        setEvents(collection);
        return this;
    }
}
