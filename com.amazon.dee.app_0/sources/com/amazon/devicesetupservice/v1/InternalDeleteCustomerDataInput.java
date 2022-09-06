package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class InternalDeleteCustomerDataInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalDeleteCustomerDataInput");
    private String customerId;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalDeleteCustomerDataInput)) {
            return false;
        }
        return Helper.equals(this.customerId, ((InternalDeleteCustomerDataInput) obj).customerId);
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.customerId);
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }
}
