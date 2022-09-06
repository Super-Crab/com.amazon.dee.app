package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ServiceRequest {
    @JsonProperty("clientName")
    private String clientName;

    protected boolean canEqual(Object obj) {
        return obj instanceof ServiceRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ServiceRequest)) {
            return false;
        }
        ServiceRequest serviceRequest = (ServiceRequest) obj;
        if (!serviceRequest.canEqual(this)) {
            return false;
        }
        String clientName = getClientName();
        String clientName2 = serviceRequest.getClientName();
        return clientName != null ? clientName.equals(clientName2) : clientName2 == null;
    }

    public String getClientName() {
        return this.clientName;
    }

    public int hashCode() {
        String clientName = getClientName();
        return 59 + (clientName == null ? 43 : clientName.hashCode());
    }

    @JsonProperty("clientName")
    public void setClientName(String str) {
        this.clientName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ServiceRequest(clientName=");
        outline107.append(getClientName());
        outline107.append(")");
        return outline107.toString();
    }
}
