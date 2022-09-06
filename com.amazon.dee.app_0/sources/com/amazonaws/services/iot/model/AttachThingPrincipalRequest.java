package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AttachThingPrincipalRequest extends AmazonWebServiceRequest implements Serializable {
    private String principal;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttachThingPrincipalRequest)) {
            return false;
        }
        AttachThingPrincipalRequest attachThingPrincipalRequest = (AttachThingPrincipalRequest) obj;
        if ((attachThingPrincipalRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (attachThingPrincipalRequest.getThingName() != null && !attachThingPrincipalRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((attachThingPrincipalRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        return attachThingPrincipalRequest.getPrincipal() == null || attachThingPrincipalRequest.getPrincipal().equals(getPrincipal());
    }

    public String getPrincipal() {
        return this.principal;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31;
        if (getPrincipal() != null) {
            i = getPrincipal().hashCode();
        }
        return hashCode + i;
    }

    public void setPrincipal(String str) {
        this.principal = str;
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
        if (getPrincipal() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("principal: ");
            outline1073.append(getPrincipal());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AttachThingPrincipalRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }

    public AttachThingPrincipalRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
