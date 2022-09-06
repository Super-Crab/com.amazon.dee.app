package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class InternalGetCustomerProvisioneeZeroTouchSetupStatusOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalGetCustomerProvisioneeZeroTouchSetupStatusOutput");
    private Boolean activeZeroTouchSetupSession;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalGetCustomerProvisioneeZeroTouchSetupStatusOutput)) {
            return false;
        }
        return Helper.equals(this.activeZeroTouchSetupSession, ((InternalGetCustomerProvisioneeZeroTouchSetupStatusOutput) obj).activeZeroTouchSetupSession);
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.activeZeroTouchSetupSession);
    }

    public Boolean isActiveZeroTouchSetupSession() {
        return this.activeZeroTouchSetupSession;
    }

    public void setActiveZeroTouchSetupSession(Boolean bool) {
        this.activeZeroTouchSetupSession = bool;
    }
}
