package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class InternalDeleteCustomerDataOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalDeleteCustomerDataOutput");
    private Boolean isDataPresent;
    private Boolean isDeletionSuccessful;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalDeleteCustomerDataOutput)) {
            return false;
        }
        InternalDeleteCustomerDataOutput internalDeleteCustomerDataOutput = (InternalDeleteCustomerDataOutput) obj;
        return Helper.equals(this.isDataPresent, internalDeleteCustomerDataOutput.isDataPresent) && Helper.equals(this.isDeletionSuccessful, internalDeleteCustomerDataOutput.isDeletionSuccessful);
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.isDataPresent, this.isDeletionSuccessful);
    }

    public Boolean isIsDataPresent() {
        return this.isDataPresent;
    }

    public Boolean isIsDeletionSuccessful() {
        return this.isDeletionSuccessful;
    }

    public void setIsDataPresent(Boolean bool) {
        this.isDataPresent = bool;
    }

    public void setIsDeletionSuccessful(Boolean bool) {
        this.isDeletionSuccessful = bool;
    }
}
