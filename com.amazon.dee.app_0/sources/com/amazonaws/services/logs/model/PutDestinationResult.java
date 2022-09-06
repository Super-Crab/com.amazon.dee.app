package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutDestinationResult implements Serializable {
    private Destination destination;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutDestinationResult)) {
            return false;
        }
        PutDestinationResult putDestinationResult = (PutDestinationResult) obj;
        if ((putDestinationResult.getDestination() == null) ^ (getDestination() == null)) {
            return false;
        }
        return putDestinationResult.getDestination() == null || putDestinationResult.getDestination().equals(getDestination());
    }

    public Destination getDestination() {
        return this.destination;
    }

    public int hashCode() {
        return 31 + (getDestination() == null ? 0 : getDestination().hashCode());
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDestination() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("destination: ");
            outline1072.append(getDestination());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutDestinationResult withDestination(Destination destination) {
        this.destination = destination;
        return this;
    }
}
