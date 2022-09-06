package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteThingTypeResult implements Serializable {
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteThingTypeResult)) {
            return false;
        }
        DeleteThingTypeResult deleteThingTypeResult = (DeleteThingTypeResult) obj;
        return true;
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline72(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
