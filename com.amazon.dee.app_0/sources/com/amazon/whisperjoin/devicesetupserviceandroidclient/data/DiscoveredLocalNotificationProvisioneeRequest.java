package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DiscoveredLocalNotificationProvisioneeRequest extends AbstractDiscoveredProvisioneeRequest {
    private final int mAdvertisedSdkVersionIndex;
    private final int mAdvertisementVersion;
    private final String mAuthMaterialIndex;
    private final int mAuthenticationMode;
    private final String mConnectivityErrorCode;
    private final String mNonce;
    private final String mProductIndex;
    private final ProvisionableInfo mProvisioneeInfo;
    private final String mRadio;
    private final int mRssi;

    public DiscoveredLocalNotificationProvisioneeRequest(String str, int i, String str2, String str3, ProvisionableInfo provisionableInfo, String str4, String str5, int i2, int i3, ProvisionerInfo provisionerInfo, int i4) {
        this.mRadio = str;
        this.mRssi = i;
        this.mProductIndex = str2;
        this.mAuthMaterialIndex = str3;
        this.mProvisioneeInfo = provisionableInfo;
        this.mConnectivityErrorCode = str4;
        this.mNonce = str5;
        this.mAuthenticationMode = i2;
        this.mAdvertisementVersion = i3;
        this.mProvisionerInfo = provisionerInfo;
        this.mAdvertisedSdkVersionIndex = i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DiscoveredLocalNotificationProvisioneeRequest.class != obj.getClass()) {
            return false;
        }
        DiscoveredLocalNotificationProvisioneeRequest discoveredLocalNotificationProvisioneeRequest = (DiscoveredLocalNotificationProvisioneeRequest) obj;
        return Objects.equal(this.mRadio, discoveredLocalNotificationProvisioneeRequest.mRadio) && Objects.equal(Integer.valueOf(this.mRssi), Integer.valueOf(discoveredLocalNotificationProvisioneeRequest.mRssi)) && Objects.equal(this.mProductIndex, discoveredLocalNotificationProvisioneeRequest.mProductIndex) && Objects.equal(this.mAuthMaterialIndex, discoveredLocalNotificationProvisioneeRequest.mAuthMaterialIndex) && Objects.equal(this.mProvisioneeInfo, discoveredLocalNotificationProvisioneeRequest.mProvisioneeInfo) && Objects.equal(this.mConnectivityErrorCode, discoveredLocalNotificationProvisioneeRequest.mConnectivityErrorCode) && Objects.equal(this.mNonce, discoveredLocalNotificationProvisioneeRequest.mNonce) && Objects.equal(Integer.valueOf(this.mAuthenticationMode), Integer.valueOf(discoveredLocalNotificationProvisioneeRequest.mAuthenticationMode)) && Objects.equal(Integer.valueOf(this.mAdvertisementVersion), Integer.valueOf(discoveredLocalNotificationProvisioneeRequest.mAdvertisementVersion)) && Objects.equal(this.mProvisionerInfo, discoveredLocalNotificationProvisioneeRequest.mProvisionerInfo) && Objects.equal(Integer.valueOf(this.mAdvertisedSdkVersionIndex), Integer.valueOf(discoveredLocalNotificationProvisioneeRequest.mAdvertisedSdkVersionIndex));
    }

    public int getAdvertisedSdkVersionIndex() {
        return this.mAdvertisedSdkVersionIndex;
    }

    public int getAdvertisementVersion() {
        return this.mAdvertisementVersion;
    }

    public String getAuthMaterialIndex() {
        return this.mAuthMaterialIndex;
    }

    public int getAuthenticationMode() {
        return this.mAuthenticationMode;
    }

    public String getConnectivityErrorCode() {
        return this.mConnectivityErrorCode;
    }

    public String getNonce() {
        return this.mNonce;
    }

    public String getProductIndex() {
        return this.mProductIndex;
    }

    public ProvisionableInfo getProvisioneeInfo() {
        return this.mProvisioneeInfo;
    }

    public String getRadio() {
        return this.mRadio;
    }

    public int getRssi() {
        return this.mRssi;
    }

    public int hashCode() {
        return Objects.hashCode(this.mRadio, Integer.valueOf(this.mRssi), this.mProductIndex, this.mAuthMaterialIndex, this.mProvisioneeInfo, this.mConnectivityErrorCode, this.mNonce, Integer.valueOf(this.mAuthenticationMode), Integer.valueOf(this.mAdvertisementVersion), this.mProvisionerInfo, Integer.valueOf(this.mAdvertisedSdkVersionIndex));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mRadio", this.mRadio).add("mRssi", this.mRssi).add("mProductIndex", this.mProductIndex).add("mAuthMaterialIndex", this.mAuthMaterialIndex).add("mProvisioneeInfo", this.mProvisioneeInfo).add("mConnectivityErrorCode", this.mConnectivityErrorCode).add("mNonce", this.mNonce).add("mAuthenticationMode", this.mAuthenticationMode).add("mAdvertisementVersion", this.mAdvertisementVersion).add("mProvisionerInfo", this.mProvisionerInfo).add("mAdvertisedSdkVersionIndex", this.mAdvertisedSdkVersionIndex).toString();
    }
}
