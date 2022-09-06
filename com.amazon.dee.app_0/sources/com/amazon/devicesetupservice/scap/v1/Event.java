package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.ErrorInfo;
/* loaded from: classes12.dex */
public class Event implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.Event");
    private ActionDetails actionDetails;
    private BleEventParameters bleParameters;
    private String cblToken;
    private ErrorInfo errorDetails;
    private int sequenceNumber;
    private String timestamp;
    private String type;

    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        return Helper.equals(this.type, event.type) && Helper.equals(this.bleParameters, event.bleParameters) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(event.sequenceNumber)) && Helper.equals(this.cblToken, event.cblToken) && Helper.equals(this.errorDetails, event.errorDetails) && Helper.equals(this.actionDetails, event.actionDetails) && Helper.equals(this.timestamp, event.timestamp);
    }

    public ActionDetails getActionDetails() {
        return this.actionDetails;
    }

    public BleEventParameters getBleParameters() {
        return this.bleParameters;
    }

    public String getCblToken() {
        return this.cblToken;
    }

    public ErrorInfo getErrorDetails() {
        return this.errorDetails;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.type, this.bleParameters, Integer.valueOf(this.sequenceNumber), this.cblToken, this.errorDetails, this.actionDetails, this.timestamp);
    }

    public void setActionDetails(ActionDetails actionDetails) {
        this.actionDetails = actionDetails;
    }

    public void setBleParameters(BleEventParameters bleEventParameters) {
        this.bleParameters = bleEventParameters;
    }

    public void setCblToken(String str) {
        this.cblToken = str;
    }

    public void setErrorDetails(ErrorInfo errorInfo) {
        this.errorDetails = errorInfo;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
