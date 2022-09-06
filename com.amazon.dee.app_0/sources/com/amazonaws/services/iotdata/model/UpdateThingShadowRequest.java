package com.amazonaws.services.iotdata.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class UpdateThingShadowRequest extends AmazonWebServiceRequest implements Serializable {
    private ByteBuffer payload;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingShadowRequest)) {
            return false;
        }
        UpdateThingShadowRequest updateThingShadowRequest = (UpdateThingShadowRequest) obj;
        if ((updateThingShadowRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (updateThingShadowRequest.getThingName() != null && !updateThingShadowRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((updateThingShadowRequest.getPayload() == null) ^ (getPayload() == null)) {
            return false;
        }
        return updateThingShadowRequest.getPayload() == null || updateThingShadowRequest.getPayload().equals(getPayload());
    }

    public ByteBuffer getPayload() {
        return this.payload;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31;
        if (getPayload() != null) {
            i = getPayload().hashCode();
        }
        return hashCode + i;
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPayload() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("payload: ");
            outline1073.append(getPayload());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateThingShadowRequest withPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
        return this;
    }

    public UpdateThingShadowRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
