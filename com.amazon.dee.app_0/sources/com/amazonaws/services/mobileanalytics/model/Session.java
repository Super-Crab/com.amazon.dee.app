package com.amazonaws.services.mobileanalytics.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
@Deprecated
/* loaded from: classes13.dex */
public class Session implements Serializable {
    private Long duration;
    private String id;
    private String startTimestamp;
    private String stopTimestamp;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Session)) {
            return false;
        }
        Session session = (Session) obj;
        if ((session.getId() == null) ^ (getId() == null)) {
            return false;
        }
        if (session.getId() != null && !session.getId().equals(getId())) {
            return false;
        }
        if ((session.getDuration() == null) ^ (getDuration() == null)) {
            return false;
        }
        if (session.getDuration() != null && !session.getDuration().equals(getDuration())) {
            return false;
        }
        if ((session.getStartTimestamp() == null) ^ (getStartTimestamp() == null)) {
            return false;
        }
        if (session.getStartTimestamp() != null && !session.getStartTimestamp().equals(getStartTimestamp())) {
            return false;
        }
        if ((session.getStopTimestamp() == null) ^ (getStopTimestamp() == null)) {
            return false;
        }
        return session.getStopTimestamp() == null || session.getStopTimestamp().equals(getStopTimestamp());
    }

    public Long getDuration() {
        return this.duration;
    }

    public String getId() {
        return this.id;
    }

    public String getStartTimestamp() {
        return this.startTimestamp;
    }

    public String getStopTimestamp() {
        return this.stopTimestamp;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getId() == null ? 0 : getId().hashCode()) + 31) * 31) + (getDuration() == null ? 0 : getDuration().hashCode())) * 31) + (getStartTimestamp() == null ? 0 : getStartTimestamp().hashCode())) * 31;
        if (getStopTimestamp() != null) {
            i = getStopTimestamp().hashCode();
        }
        return hashCode + i;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setStartTimestamp(String str) {
        this.startTimestamp = str;
    }

    public void setStopTimestamp(String str) {
        this.stopTimestamp = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("id: ");
            outline1072.append(getId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDuration() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("duration: ");
            outline1073.append(getDuration());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getStartTimestamp() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("startTimestamp: ");
            outline1074.append(getStartTimestamp());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStopTimestamp() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("stopTimestamp: ");
            outline1075.append(getStopTimestamp());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Session withDuration(Long l) {
        this.duration = l;
        return this;
    }

    public Session withId(String str) {
        this.id = str;
        return this;
    }

    public Session withStartTimestamp(String str) {
        this.startTimestamp = str;
        return this;
    }

    public Session withStopTimestamp(String str) {
        this.stopTimestamp = str;
        return this;
    }
}
