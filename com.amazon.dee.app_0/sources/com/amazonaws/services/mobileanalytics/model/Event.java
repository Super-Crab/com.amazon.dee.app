package com.amazonaws.services.mobileanalytics.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@Deprecated
/* loaded from: classes13.dex */
public class Event implements Serializable {
    private Map<String, String> attributes;
    private String eventType;
    private Map<String, Double> metrics;
    private Session session;
    private String timestamp;
    private String version;

    public Event addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (!this.attributes.containsKey(str)) {
            this.attributes.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public Event addmetricsEntry(String str, Double d) {
        if (this.metrics == null) {
            this.metrics = new HashMap();
        }
        if (!this.metrics.containsKey(str)) {
            this.metrics.put(str, d);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public Event clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public Event clearmetricsEntries() {
        this.metrics = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        if ((event.getEventType() == null) ^ (getEventType() == null)) {
            return false;
        }
        if (event.getEventType() != null && !event.getEventType().equals(getEventType())) {
            return false;
        }
        if ((event.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        if (event.getTimestamp() != null && !event.getTimestamp().equals(getTimestamp())) {
            return false;
        }
        if ((event.getSession() == null) ^ (getSession() == null)) {
            return false;
        }
        if (event.getSession() != null && !event.getSession().equals(getSession())) {
            return false;
        }
        if ((event.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (event.getVersion() != null && !event.getVersion().equals(getVersion())) {
            return false;
        }
        if ((event.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (event.getAttributes() != null && !event.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((event.getMetrics() == null) ^ (getMetrics() == null)) {
            return false;
        }
        return event.getMetrics() == null || event.getMetrics().equals(getMetrics());
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public String getEventType() {
        return this.eventType;
    }

    public Map<String, Double> getMetrics() {
        return this.metrics;
    }

    public Session getSession() {
        return this.session;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getEventType() == null ? 0 : getEventType().hashCode()) + 31) * 31) + (getTimestamp() == null ? 0 : getTimestamp().hashCode())) * 31) + (getSession() == null ? 0 : getSession().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31) + (getAttributes() == null ? 0 : getAttributes().hashCode())) * 31;
        if (getMetrics() != null) {
            i = getMetrics().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setEventType(String str) {
        this.eventType = str;
    }

    public void setMetrics(Map<String, Double> map) {
        this.metrics = map;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEventType() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("eventType: ");
            outline1072.append(getEventType());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTimestamp() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("timestamp: ");
            outline1073.append(getTimestamp());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSession() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("session: ");
            outline1074.append(getSession());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getVersion() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("version: ");
            outline1075.append(getVersion());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getAttributes() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("attributes: ");
            outline1076.append(getAttributes());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getMetrics() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("metrics: ");
            outline1077.append(getMetrics());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Event withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public Event withEventType(String str) {
        this.eventType = str;
        return this;
    }

    public Event withMetrics(Map<String, Double> map) {
        this.metrics = map;
        return this;
    }

    public Event withSession(Session session) {
        this.session = session;
        return this;
    }

    public Event withTimestamp(String str) {
        this.timestamp = str;
        return this;
    }

    public Event withVersion(String str) {
        this.version = str;
        return this;
    }
}
