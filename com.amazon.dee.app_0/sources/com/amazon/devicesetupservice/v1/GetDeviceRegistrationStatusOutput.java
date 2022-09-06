package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetDeviceRegistrationStatusOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetDeviceRegistrationStatusOutput");
    private String durationToWait;
    private String lastRegisteredTime;
    private String registrationState;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetDeviceRegistrationStatusOutput)) {
            return false;
        }
        GetDeviceRegistrationStatusOutput getDeviceRegistrationStatusOutput = (GetDeviceRegistrationStatusOutput) obj;
        return Helper.equals(this.lastRegisteredTime, getDeviceRegistrationStatusOutput.lastRegisteredTime) && Helper.equals(this.durationToWait, getDeviceRegistrationStatusOutput.durationToWait) && Helper.equals(this.registrationState, getDeviceRegistrationStatusOutput.registrationState);
    }

    public String getDurationToWait() {
        return this.durationToWait;
    }

    public String getLastRegisteredTime() {
        return this.lastRegisteredTime;
    }

    public String getRegistrationState() {
        return this.registrationState;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.lastRegisteredTime, this.durationToWait, this.registrationState);
    }

    public void setDurationToWait(String str) {
        this.durationToWait = str;
    }

    public void setLastRegisteredTime(String str) {
        this.lastRegisteredTime = str;
    }

    public void setRegistrationState(String str) {
        this.registrationState = str;
    }
}
