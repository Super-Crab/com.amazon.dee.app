package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetProvisionableStatusOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetProvisionableStatusOutput");
    private String endpointToUse;
    private String event;
    private int sequenceNumber;
    private String sessionToken;
    private String state;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetProvisionableStatusOutput)) {
            return false;
        }
        GetProvisionableStatusOutput getProvisionableStatusOutput = (GetProvisionableStatusOutput) obj;
        return Helper.equals(this.sessionToken, getProvisionableStatusOutput.sessionToken) && Helper.equals(this.event, getProvisionableStatusOutput.event) && Helper.equals(this.endpointToUse, getProvisionableStatusOutput.endpointToUse) && Helper.equals(this.state, getProvisionableStatusOutput.state) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(getProvisionableStatusOutput.sequenceNumber));
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public String getEvent() {
        return this.event;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public String getState() {
        return this.state;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.sessionToken, this.event, this.endpointToUse, this.state, Integer.valueOf(this.sequenceNumber));
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setEvent(String str) {
        this.event = str;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public void setState(String str) {
        this.state = str;
    }
}
