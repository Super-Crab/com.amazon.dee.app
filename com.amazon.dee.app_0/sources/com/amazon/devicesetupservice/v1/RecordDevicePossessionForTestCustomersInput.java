package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class RecordDevicePossessionForTestCustomersInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.RecordDevicePossessionForTestCustomersInput");
    private String deviceSerial;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof RecordDevicePossessionForTestCustomersInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.deviceSerial, ((RecordDevicePossessionForTestCustomersInput) obj).deviceSerial);
    }

    public String getDeviceSerial() {
        return this.deviceSerial;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.deviceSerial);
    }

    public void setDeviceSerial(String str) {
        this.deviceSerial = str;
    }
}
