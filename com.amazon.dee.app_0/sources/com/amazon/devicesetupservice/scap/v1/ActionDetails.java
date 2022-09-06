package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ActionDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.ActionDetails");
    private int sequenceNumber;
    private String type;

    public boolean equals(Object obj) {
        if (!(obj instanceof ActionDetails)) {
            return false;
        }
        ActionDetails actionDetails = (ActionDetails) obj;
        return Helper.equals(this.type, actionDetails.type) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(actionDetails.sequenceNumber));
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.type, Integer.valueOf(this.sequenceNumber));
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setType(String str) {
        this.type = str;
    }
}
