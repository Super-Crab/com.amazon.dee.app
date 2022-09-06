package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.WifiScanData;
import com.amazon.devicesetupservice.ProvisioneeIdentifier;
/* loaded from: classes12.dex */
public class ProvisioneeSetupInformation implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.ProvisioneeSetupInformation");
    private AuthMaterialIdentifier authMaterialIdentifier;
    private String errorCode;
    private String lastUpdatedTime;
    private ProvisioneeIdentifier provisioneeIdentifier;
    private LegacyIdentifier provisionerInformation;
    private String provisioningMethod;
    private String provisioningState;
    private String provisioningStatus;
    private WifiScanData wifiNetworkInfo;

    public boolean equals(Object obj) {
        if (!(obj instanceof ProvisioneeSetupInformation)) {
            return false;
        }
        ProvisioneeSetupInformation provisioneeSetupInformation = (ProvisioneeSetupInformation) obj;
        return Helper.equals(this.authMaterialIdentifier, provisioneeSetupInformation.authMaterialIdentifier) && Helper.equals(this.provisioningStatus, provisioneeSetupInformation.provisioningStatus) && Helper.equals(this.provisioningState, provisioneeSetupInformation.provisioningState) && Helper.equals(this.lastUpdatedTime, provisioneeSetupInformation.lastUpdatedTime) && Helper.equals(this.provisioneeIdentifier, provisioneeSetupInformation.provisioneeIdentifier) && Helper.equals(this.provisioningMethod, provisioneeSetupInformation.provisioningMethod) && Helper.equals(this.wifiNetworkInfo, provisioneeSetupInformation.wifiNetworkInfo) && Helper.equals(this.provisionerInformation, provisioneeSetupInformation.provisionerInformation) && Helper.equals(this.errorCode, provisioneeSetupInformation.errorCode);
    }

    public AuthMaterialIdentifier getAuthMaterialIdentifier() {
        return this.authMaterialIdentifier;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public ProvisioneeIdentifier getProvisioneeIdentifier() {
        return this.provisioneeIdentifier;
    }

    public LegacyIdentifier getProvisionerInformation() {
        return this.provisionerInformation;
    }

    public String getProvisioningMethod() {
        return this.provisioningMethod;
    }

    public String getProvisioningState() {
        return this.provisioningState;
    }

    public String getProvisioningStatus() {
        return this.provisioningStatus;
    }

    public WifiScanData getWifiNetworkInfo() {
        return this.wifiNetworkInfo;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.authMaterialIdentifier, this.provisioningStatus, this.provisioningState, this.lastUpdatedTime, this.provisioneeIdentifier, this.provisioningMethod, this.wifiNetworkInfo, this.provisionerInformation, this.errorCode);
    }

    public void setAuthMaterialIdentifier(AuthMaterialIdentifier authMaterialIdentifier) {
        this.authMaterialIdentifier = authMaterialIdentifier;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setLastUpdatedTime(String str) {
        this.lastUpdatedTime = str;
    }

    public void setProvisioneeIdentifier(ProvisioneeIdentifier provisioneeIdentifier) {
        this.provisioneeIdentifier = provisioneeIdentifier;
    }

    public void setProvisionerInformation(LegacyIdentifier legacyIdentifier) {
        this.provisionerInformation = legacyIdentifier;
    }

    public void setProvisioningMethod(String str) {
        this.provisioningMethod = str;
    }

    public void setProvisioningState(String str) {
        this.provisioningState = str;
    }

    public void setProvisioningStatus(String str) {
        this.provisioningStatus = str;
    }

    public void setWifiNetworkInfo(WifiScanData wifiScanData) {
        this.wifiNetworkInfo = wifiScanData;
    }
}
