package com.amazonaws.services.iotdata.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class DeleteThingShadowResult implements Serializable {
    private ByteBuffer payload;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteThingShadowResult)) {
            return false;
        }
        DeleteThingShadowResult deleteThingShadowResult = (DeleteThingShadowResult) obj;
        if ((deleteThingShadowResult.getPayload() == null) ^ (getPayload() == null)) {
            return false;
        }
        return deleteThingShadowResult.getPayload() == null || deleteThingShadowResult.getPayload().equals(getPayload());
    }

    public ByteBuffer getPayload() {
        return this.payload;
    }

    public int hashCode() {
        return 31 + (getPayload() == null ? 0 : getPayload().hashCode());
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPayload() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("payload: ");
            outline1072.append(getPayload());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteThingShadowResult withPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
        return this;
    }
}
