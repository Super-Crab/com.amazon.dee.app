package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ThingConnectivity implements Serializable {
    private Boolean connected;
    private Long timestamp;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingConnectivity)) {
            return false;
        }
        ThingConnectivity thingConnectivity = (ThingConnectivity) obj;
        if ((thingConnectivity.getConnected() == null) ^ (getConnected() == null)) {
            return false;
        }
        if (thingConnectivity.getConnected() != null && !thingConnectivity.getConnected().equals(getConnected())) {
            return false;
        }
        if ((thingConnectivity.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        return thingConnectivity.getTimestamp() == null || thingConnectivity.getTimestamp().equals(getTimestamp());
    }

    public Boolean getConnected() {
        return this.connected;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getConnected() == null ? 0 : getConnected().hashCode()) + 31) * 31;
        if (getTimestamp() != null) {
            i = getTimestamp().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isConnected() {
        return this.connected;
    }

    public void setConnected(Boolean bool) {
        this.connected = bool;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getConnected() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("connected: ");
            outline1072.append(getConnected());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTimestamp() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("timestamp: ");
            outline1073.append(getTimestamp());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingConnectivity withConnected(Boolean bool) {
        this.connected = bool;
        return this;
    }

    public ThingConnectivity withTimestamp(Long l) {
        this.timestamp = l;
        return this;
    }
}
