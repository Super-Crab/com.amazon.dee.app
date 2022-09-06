package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteDestinationRequest extends AmazonWebServiceRequest implements Serializable {
    private String destinationName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteDestinationRequest)) {
            return false;
        }
        DeleteDestinationRequest deleteDestinationRequest = (DeleteDestinationRequest) obj;
        if ((deleteDestinationRequest.getDestinationName() == null) ^ (getDestinationName() == null)) {
            return false;
        }
        return deleteDestinationRequest.getDestinationName() == null || deleteDestinationRequest.getDestinationName().equals(getDestinationName());
    }

    public String getDestinationName() {
        return this.destinationName;
    }

    public int hashCode() {
        return 31 + (getDestinationName() == null ? 0 : getDestinationName().hashCode());
    }

    public void setDestinationName(String str) {
        this.destinationName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDestinationName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("destinationName: ");
            outline1072.append(getDestinationName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteDestinationRequest withDestinationName(String str) {
        this.destinationName = str;
        return this;
    }
}
