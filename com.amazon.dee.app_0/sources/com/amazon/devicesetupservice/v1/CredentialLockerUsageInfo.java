package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class CredentialLockerUsageInfo implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.CredentialLockerUsageInfo");
    private String chosenSSIDCredChanged;
    private String sSIDFromCredlocker;
    private String ssidFromCredlocker;
    private String userIntendsToSaveCredentialToLocker;

    public boolean equals(Object obj) {
        if (!(obj instanceof CredentialLockerUsageInfo)) {
            return false;
        }
        CredentialLockerUsageInfo credentialLockerUsageInfo = (CredentialLockerUsageInfo) obj;
        return Helper.equals(this.chosenSSIDCredChanged, credentialLockerUsageInfo.chosenSSIDCredChanged) && Helper.equals(this.ssidFromCredlocker, credentialLockerUsageInfo.ssidFromCredlocker) && Helper.equals(this.sSIDFromCredlocker, credentialLockerUsageInfo.sSIDFromCredlocker) && Helper.equals(this.userIntendsToSaveCredentialToLocker, credentialLockerUsageInfo.userIntendsToSaveCredentialToLocker);
    }

    public String getChosenSSIDCredChanged() {
        return this.chosenSSIDCredChanged;
    }

    @Deprecated
    public String getSSIDFromCredlocker() {
        return this.sSIDFromCredlocker;
    }

    public String getSsidFromCredlocker() {
        return this.ssidFromCredlocker;
    }

    public String getUserIntendsToSaveCredentialToLocker() {
        return this.userIntendsToSaveCredentialToLocker;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.chosenSSIDCredChanged, this.ssidFromCredlocker, this.sSIDFromCredlocker, this.userIntendsToSaveCredentialToLocker);
    }

    public void setChosenSSIDCredChanged(String str) {
        this.chosenSSIDCredChanged = str;
    }

    @Deprecated
    public void setSSIDFromCredlocker(String str) {
        this.sSIDFromCredlocker = str;
    }

    public void setSsidFromCredlocker(String str) {
        this.ssidFromCredlocker = str;
    }

    public void setUserIntendsToSaveCredentialToLocker(String str) {
        this.userIntendsToSaveCredentialToLocker = str;
    }
}
